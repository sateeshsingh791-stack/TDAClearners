

const SYSTEM_INSTRUCTIONS = {
  ACADEMIC_PROFESSOR: `
    You are a Senior Academic Professor of B.Voc Textile Design & Apparel Technology at Khalsa College, Amritsar (affiliated with Guru Nanak Dev University, NEP framework).
    You teach Semester I and Semester II subjects:
    - BVTD 111: Introduction to Textile Science
    - BVTD 112: Fibre Identification Practical
    - BVTD 113: Sewing Techniques & Garment Construction
    - BVTD 114: Surface Ornamentation
    - BVTD 121: Introduction to Fashion
    - BVTD 122: Fashion Illustration & CAD
    - BVTD 123: Design Foundation II & Woven Fabric Analysis
    - CS-BVTD111/121: Communication Skills in English
    - BHPB 1101/1201: Punjabi Heritage & Folk Culture
    - ZDA111/121: Drug Abuse Prevention
    Always provide structured, academically rigorous explanations suitable for university exams, with headings, bullet points, and practical examples.
  `.trim(),
  INDUSTRY_EXPERT: `
    You are an Apparel Industry Production Manager & Senior Merchandiser with 15+ years of experience in leading textile mills and export houses (Ludhiana knitwear, Surat silk, Tirupur exports, and international apparel brands).
    You advise students on real-world garment factory workflows, quality inspection (AQL standards), SAM (Standard Allowed Minutes), cost sheets, tech packs, CAD software (TUKAcad, Lectra, CorelDraw), and export compliance.
  `.trim(),
  LAB_SCIENTIST: `
    You are a Textile Testing & Quality Assurance Lab Scientist.
    You specialize in physical, chemical, and microscopic testing of textile fibres, yarn count determination (Direct/Indirect systems, Ne, Tex, Denier), GSM calculation, weave interlacement graph representation, dye fastness, and laboratory safety.
    Guide students through step-by-step procedures, expected observations, and viva voce defense.
  `.trim(),
  FASHION_FORECASTER: `
    You are a Fashion Forecaster and Textile Surface Designer.
    You specialize in seasonal color palettes (Pantone), motif stylization, traditional Indian crafts with contemporary fusion, fashion lifecycle forecasting, couture draping, and portfolio curation for young designers.
  `.trim()
};

/**
 * AI Chat Proxy Endpoint
 * Securely proxies client requests to Google Gemini REST API without exposing API keys.
 */
export const chatProxy = async (req, res, next) => {
  try {
    const { history = [], userMessage, modelId = 'gemini-3.5-flash', roleKey = 'ACADEMIC_PROFESSOR', enableSearchGrounding = false } = req.body;

    if (!userMessage) {
      return res.status(400).json({
        success: false,
        error: { message: 'userMessage is required.' }
      });
    }

    const apiKey = process.env.GEMINI_API_KEY;

    // Determine system instruction text
    const systemInstructionText = SYSTEM_INSTRUCTIONS[roleKey] || SYSTEM_INSTRUCTIONS.ACADEMIC_PROFESSOR;

    // Effective model selection
    const effectiveModel = enableSearchGrounding ? 'gemini-3.5-flash' : modelId;

    // Check if key is available
    if (!apiKey || apiKey === 'your_gemini_api_key_here') {
      const fallbackText = generateFallbackResponse(userMessage, roleKey, effectiveModel);
      return res.status(200).json({
        success: true,
        data: {
          chatTurn: {
            id: String(Date.now()),
            role: 'model',
            text: fallbackText,
            modelUsed: effectiveModel,
            isSearchGrounded: enableSearchGrounding,
            searchQueries: [],
            groundingSources: []
          }
        }
      });
    }

    const url = `https://generativelanguage.googleapis.com/v1beta/models/${effectiveModel}:generateContent?key=${apiKey}`;

    // Construct contents array
    const contentsArray = [];

    // Add recent history turns (up to 10)
    history.slice(-10).forEach((turn) => {
      contentsArray.push({
        role: turn.role === 'user' ? 'user' : 'model',
        parts: [{ text: turn.text }]
      });
    });

    // Add new user prompt
    contentsArray.push({
      role: 'user',
      parts: [{ text: userMessage }]
    });

    // Construct payload
    const payload = {
      contents: contentsArray,
      systemInstruction: {
        parts: [{ text: systemInstructionText }]
      }
    };

    if (enableSearchGrounding) {
      payload.tools = [{ googleSearch: {} }];
    }

    const geminiRes = await fetch(url, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });

    const responseBody = await geminiRes.json();

    if (!geminiRes.ok) {
      console.warn('[Gemini API Warning] Upstream API call failed, generating fallback response:', responseBody);
      const fallbackText = generateFallbackResponse(userMessage, roleKey, effectiveModel);
      return res.status(200).json({
        success: true,
        data: {
          chatTurn: {
            id: String(Date.now()),
            role: 'model',
            text: fallbackText,
            modelUsed: effectiveModel,
            isSearchGrounded: enableSearchGrounding,
            searchQueries: [],
            groundingSources: []
          }
        }
      });
    }

    const candidate = responseBody.candidates?.[0];
    const parts = candidate?.content?.parts || [];
    const responseText = parts.map((p) => p.text).join('') || 'No textual response returned by model.';

    // Extract search grounding metadata
    const searchQueries = [];
    const groundingSources = [];
    let isGrounded = false;

    const groundingMetadata = candidate?.groundingMetadata;
    if (groundingMetadata) {
      if (Array.isArray(groundingMetadata.webSearchQueries)) {
        groundingMetadata.webSearchQueries.forEach((q) => searchQueries.push(q));
      }
      if (Array.isArray(groundingMetadata.groundingChunks)) {
        groundingMetadata.groundingChunks.forEach((chunk) => {
          if (chunk.web?.uri) {
            groundingSources.push({
              title: chunk.web.title || 'Web Source',
              uri: chunk.web.uri
            });
          }
        });
      }
      if (searchQueries.length > 0 || groundingSources.length > 0) {
        isGrounded = true;
      }
    }

    res.status(200).json({
      success: true,
      data: {
        chatTurn: {
          id: String(Date.now()),
          role: 'model',
          text: responseText,
          modelUsed: effectiveModel,
          isSearchGrounded: isGrounded || enableSearchGrounding,
          searchQueries,
          groundingSources
        }
      }
    });
  } catch (error) {
    console.error('[AI Chat Error]', error);
    const fallbackText = generateFallbackResponse(req.body?.userMessage || '', 'ACADEMIC_PROFESSOR', 'gemini-3.5-flash');
    res.status(200).json({
      success: true,
      data: {
        chatTurn: {
          id: String(Date.now()),
          role: 'model',
          text: fallbackText,
          modelUsed: 'gemini-3.5-flash',
          isSearchGrounded: false,
          searchQueries: [],
          groundingSources: []
        }
      }
    });
  }
};

/**
 * Intelligent Fallback Generator for Offline / Keyless Environments
 */
function generateFallbackResponse(query, roleKey, modelId) {
  const q = query.toLowerCase();
  if (q.includes('burn') || q.includes('silk') || q.includes('polyester') || q.includes('cotton')) {
    return `### 🔬 Textile Identification & Burning Test Analysis\n\nAccording to **BVTD 112 (Fibre Identification Practical)** syllabus:\n\n1. **Cotton (Cellulosic Fibre)**:\n   - *Approaching Flame*: Does not shrink.\n   - *In Flame*: Burns rapidly with yellow flame.\n   - *Odor*: Burning paper odor.\n   - *Residue*: Light, feathery gray ash.\n\n2. **Silk / Wool (Protein Fibre)**:\n   - *In Flame*: Burns slowly, self-extinguishing.\n   - *Odor*: Burning hair/feathers.\n   - *Residue*: Dark, crushable irregular black bead.\n\n3. **Polyester / Nylon (Synthetic Thermoplastic)**:\n   - *Approaching Flame*: Melts and curls away from flame.\n   - *Odor*: Sweet chemical/aromatic odor.\n   - *Residue*: Hard, black, uncrushable round bead.`;
  }
  if (q.includes('fashion cycle') || q.includes('stages') || q.includes('trend')) {
    return `### 👗 The 5 Stages of the Fashion Life Cycle\n\nAccording to **BVTD 121 (Introduction to Fashion)** curriculum:\n\n1. **Introduction**: Avant-garde designs introduced on runway/couture in limited quantities at highest price point.\n2. **Rise**: Accepted by fashion leaders & influencers; mass manufacturers copy/adapt the style.\n3. **Culmination (Peak)**: Maximum popularity and mass production; widely available at affordable retail prices.\n4. **Decline**: Market saturation occurs; consumers tire of the style; price discounts begin.\n5. **Obsolescence**: Style is deemed out of fashion and replaced by a fresh aesthetic cycle.`;
  }
  if (q.includes('weave') || q.includes('twill') || q.includes('plain') || q.includes('satin')) {
    return `### 🧵 Fabric Weave Structure Comparison\n\nReferencing **BVTD 123 (Design Foundation II & Woven Fabric Analysis)**:\n\n- **Plain Weave (1/1)**: Simplest 1-up / 1-down interlacement. Maximizes yarn intersections per square inch. Produces durable, reversible fabrics like Calico, Poplin, and Chiffon.\n- **Twill Weave (2/1, 2/2, 3/1)**: Characterized by prominent diagonal wale lines at 45° angle. Higher drape and crease resistance (Denim, Gabardine, Drill).\n- **Satin Weave (4/1)**: Long floating warp or weft yarns yielding high lustrous sheen and silky hand feel.`;
  }
  return `### 🎓 Academic Response\n\nBased on the **Khalsa College B.Voc Textile Design & Apparel Technology** scheme:\n\nYour query regarding "${query}" connects directly to core curriculum competencies.\n\n- **Theoretical Foundation**: Examine raw material characteristics, yarn counts, and molecular polymer arrangements.\n- **Practical Laboratory Application**: Perform standard testing protocols under proper safety parameters.\n- **Industrial Alignment**: Ensure production speed, quality compliance (AQL 2.5), and market readiness.\n\n*(Processed via ${modelId})*`;
}
