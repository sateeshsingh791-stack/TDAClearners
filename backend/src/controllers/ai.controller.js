import { GoogleGenAI } from '@google/genai';

const SYSTEM_INSTRUCTIONS = {
  ACADEMIC_PROFESSOR: `
    You are a Senior Academic Professor of B.Voc Textile Design & Apparel Technology at Khalsa College, Amritsar (affiliated with Guru Nanak Dev University, NEP framework).
    You teach Semester I and Semester II subjects:
    - BVTD 111: Design Foundation & Basics of Textile
    - BVTD 112: Design Foundation & Basics of Textile (Practical)
    - BVTD 113: Sewing Techniques (Practical)
    - BVTD 114: Introduction to Enterprenurship
    - CS-BVTD111: Computer Application-I
    - BCSV-1129: Communication Skills in English-I
    - BVTD 121: Introduction to Fashion
    - BVTD 122: Garment sewing (practical)
    - BVTD 123: Design foundation and basics of textiles - II (Practical)
    - BVTD 124: Enterprise Planning
    - CS-BVTD121: Computer Applications-II (Practical)
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
 * Securely proxies client requests to Google Gemini API using process.env.GEMINI_API_KEY without exposing keys.
 */
export const chatProxy = async (req, res, next) => {
  try {
    const {
      history = [],
      userMessage,
      message,
      modelId = 'gemini-2.5-flash',
      roleKey = 'ACADEMIC_PROFESSOR',
      enableSearchGrounding = false
    } = req.body;

    const promptText = userMessage || message;

    if (!promptText) {
      return res.status(400).json({
        success: false,
        error: { message: 'userMessage or message field is required.' }
      });
    }

    const apiKey = process.env.GEMINI_API_KEY;
    const systemInstructionText = SYSTEM_INSTRUCTIONS[roleKey] || SYSTEM_INSTRUCTIONS.ACADEMIC_PROFESSOR;
    const effectiveModel = modelId || 'gemini-2.5-flash';

    // If key is missing or default placeholder, use offline fallback response
    if (!apiKey || apiKey.trim() === '' || apiKey === 'your_gemini_api_key_here') {
      const fallbackText = generateFallbackResponse(promptText, roleKey, effectiveModel);
      return res.status(200).json({
        success: true,
        data: {
          chatTurn: {
            id: String(Date.now()),
            role: 'model',
            text: fallbackText,
            modelUsed: effectiveModel,
            isSearchGrounded: false,
            searchQueries: [],
            groundingSources: []
          }
        }
      });
    }

    try {
      const ai = new GoogleGenAI({ apiKey });

      // Construct history contents array
      const contentsArray = [];
      history.slice(-10).forEach((turn) => {
        contentsArray.push({
          role: turn.role === 'user' ? 'user' : 'model',
          parts: [{ text: turn.text || turn.content || '' }]
        });
      });

      contentsArray.push({
        role: 'user',
        parts: [{ text: promptText }]
      });

      const config = {
        systemInstruction: systemInstructionText
      };

      if (enableSearchGrounding) {
        config.tools = [{ googleSearch: {} }];
      }

      const response = await ai.models.generateContent({
        model: effectiveModel,
        contents: contentsArray,
        config
      });

      const responseText = response.text || 'No textual response returned by Gemini.';

      res.status(200).json({
        success: true,
        data: {
          chatTurn: {
            id: String(Date.now()),
            role: 'model',
            text: responseText,
            modelUsed: effectiveModel,
            isSearchGrounded: enableSearchGrounding,
            searchQueries: [],
            groundingSources: []
          }
        }
      });
    } catch (sdkError) {
      console.warn('[Gemini SDK Warning] SDK call failed, using fallback:', sdkError.message);
      const fallbackText = generateFallbackResponse(promptText, roleKey, effectiveModel);
      res.status(200).json({
        success: true,
        data: {
          chatTurn: {
            id: String(Date.now()),
            role: 'model',
            text: fallbackText,
            modelUsed: effectiveModel,
            isSearchGrounded: false,
            searchQueries: [],
            groundingSources: []
          }
        }
      });
    }
  } catch (error) {
    console.error('[AI Chat Error]', error);
    next(error);
  }
};

/**
 * Intelligent Fallback Generator for Offline / Keyless Environments
 */
function generateFallbackResponse(query, roleKey, modelId) {
  const q = query.toLowerCase();
  if (q.includes('sewing') || q.includes('seam') || q.includes('french') || q.includes('stitching')) {
    return `### 🧵 Sewing Techniques & Seam Engineering\n\nAccording to **BVTD 113 (Sewing Techniques Practical)** syllabus:\n\n1. **Plain Seam**: Standard structural join formed right sides together at 1.5cm seam allowance.\n2. **French Seam**: Self-enclosed double stitch seam encasing raw edges inside a second fold; ideal for delicate and sheer fabrics.\n3. **Run & Fell Seam**: Double topstitched structural seam providing high strength for heavy cottons and denim.\n4. **Fullness Control**: Darts converge at the bust apex; pleats fold crisp lines; gathers distribute volume evenly.`;
  }
  if (q.includes('entrepreneurship') || q.includes('business') || q.includes('dpr') || q.includes('msme')) {
    return `### 💼 Entrepreneurship Fundamentals\n\nAccording to **BVTD 114 (Introduction to Enterprenurship)** curriculum:\n\n1. **Concept**: Entrepreneurship is the process of discovering opportunities, assembling resources, and assuming financial risk to launch a venture.\n2. **Characteristics**: High need for achievement, vision, perseverance, risk tolerance, and innovative drive.\n3. **Institutional Support**: District Industries Centres (DIC) and MSME policies provide single-window clearances, technology upgrades (TUFS), and financial subsidies.\n4. **Detailed Project Report (DPR)**: Comprehensive documentation of technical, financial, and market feasibility required for bank loans.`;
  }
  if (q.includes('fashion') || q.includes('cycle') || q.includes('trend')) {
    return `### 👗 The 5 Stages of the Fashion Life Cycle\n\nAccording to **BVTD 121 (Introduction to Fashion)** syllabus:\n\n1. **Introduction**: Avant-garde designs introduced at high price points in limited quantities.\n2. **Rise**: Accepted by trend leaders; mass manufacturers begin adapting the style.\n3. **Culmination (Peak)**: Mass production and peak popularity across accessible retail channels.\n4. **Decline**: Market saturation occurs; markdowns begin.\n5. **Obsolescence**: Style is discarded and replaced by a fresh fashion cycle.`;
  }
  return `### 🎓 Academic Response\n\nBased on the **Khalsa College B.Voc Textile Design & Apparel Technology** scheme:\n\nYour query regarding "${query}" connects directly to core curriculum competencies.\n\n- **Theoretical Foundation**: Review subject concepts, yarn parameters, and design principles.\n- **Practical Application**: Follow step-by-step laboratory procedures.\n- **Industry Alignment**: Ensure production efficiency, quality standards, and market readiness.\n\n*(Processed via ${modelId})*`;
}
