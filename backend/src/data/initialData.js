export const INITIAL_SUBJECTS = [
  {
    code: 'BVTD111',
    name: 'Introduction to Textile Science',
    semesterNumber: 1,
    yearNumber: 1,
    category: 'MAJOR',
    type: 'THEORY',
    hoursPerWeek: 4,
    lectureCredits: 4,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 4,
    theoryMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 9-10',
    overview: 'Fundamental study of natural, regenerated, and synthetic textile fibres, polymer properties, and yarn manufacturing.',
    courseObjectives: ['Classify textile fibres by origin and chemistry', 'Understand fibre physical & chemical characteristics', 'Master yarn spinning systems'],
    learningObjectives: ['Fibre classification', 'Morphology', 'Moisture regain & tensile behavior'],
    courseOutcomes: ['Ability to select appropriate fibres for specific apparel end-uses'],
    booksPrescribed: ['Textile Science by E.P. Gohl & L.D. Vilensky', 'Understanding Textiles by Phyllis G. Tortora']
  },
  {
    code: 'BVTD112',
    name: 'Fibre Identification & Sewing Practical',
    semesterNumber: 1,
    yearNumber: 1,
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 6,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 3,
    totalCredits: 3,
    practicalMarks: 50,
    internalAssessmentMarks: 25,
    totalMarks: 75,
    syllabusPageRef: 'Page 11-12',
    overview: 'Laboratory practicals on microscopic identification, burning test, chemical solubility, and lockstitch sewing machine operations.',
    courseObjectives: ['Perform microscopic & chemical fibre identification', 'Operate industrial sewing machinery safely', 'Construct basic seams & pleats'],
    booksPrescribed: ['Complete Guide to Sewing by Reader Digest']
  },
  {
    code: 'BVTD113',
    name: 'Introduction to Entrepreneurship',
    semesterNumber: 1,
    yearNumber: 1,
    category: 'MINOR',
    type: 'THEORY',
    hoursPerWeek: 3,
    lectureCredits: 3,
    totalCredits: 3,
    theoryMarks: 50,
    internalAssessmentMarks: 25,
    totalMarks: 75,
    syllabusPageRef: 'Page 13-14',
    overview: 'Entrepreneurial principles, business planning, MSME schemes, and garment enterprise management.',
    courseObjectives: ['Develop entrepreneurial mindset', 'Understand MSME policies', 'Draft Detailed Project Reports (DPR)']
  },
  {
    code: 'BVTD121',
    name: 'Introduction to Fashion & Fashion Cycle',
    semesterNumber: 2,
    yearNumber: 1,
    category: 'MAJOR',
    type: 'THEORY',
    hoursPerWeek: 4,
    lectureCredits: 4,
    totalCredits: 4,
    theoryMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 18-19',
    overview: 'Comprehensive study of fashion terminologies, fashion life cycle curve, theories of fashion adoption, and trend forecasting.',
    courseObjectives: ['Analyze fashion cycles & fad patterns', 'Master fashion adoption theories', 'Evaluate consumer buying behavior']
  },
  {
    code: 'BVTD122',
    name: 'Fashion Illustration & Garment Construction Practical',
    semesterNumber: 2,
    yearNumber: 1,
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 6,
    practicalCredits: 3,
    totalCredits: 3,
    practicalMarks: 50,
    internalAssessmentMarks: 25,
    totalMarks: 75,
    syllabusPageRef: 'Page 20-21',
    overview: 'Pattern drafting, 8-head croquis figure sketching, bodice block creation, and dart manipulation.',
    courseObjectives: ['Draft basic bodice foundation blocks', 'Manipulate bust darts cleanly', 'Sketch fashion croquis']
  },
  {
    code: 'BVTD123',
    name: 'Design Foundation II & Woven Fabric Analysis',
    semesterNumber: 2,
    yearNumber: 1,
    category: 'MINOR',
    type: 'THEORY_AND_PRACTICAL',
    hoursPerWeek: 4,
    totalCredits: 3,
    theoryMarks: 35,
    practicalMarks: 25,
    internalAssessmentMarks: 15,
    totalMarks: 75,
    syllabusPageRef: 'Page 22-23',
    overview: 'Woven fabric structures (Plain, Twill, Satin), point paper graph representation, reed-pick analysis, and GSM calculations.'
  }
];

export const INITIAL_TOPICS = [
  {
    topicId: 'bvtd111_u1_t1',
    subjectCode: 'BVTD111',
    unitNumber: 1,
    unitTitle: 'Unit 1: Introduction to Textile Fibres',
    title: 'Classification & Physical Properties of Textile Fibres',
    overview: 'Comprehensive analysis of natural (cotton, flax, silk, wool) and synthetic (nylon, polyester, acrylic) fibres.',
    keyPoints: [
      'Fibres are classified into Natural (Plant, Animal, Mineral) and Manufactured (Regenerated, Synthetic).',
      'Cotton is composed of 88-96% cellulose, displaying natural convolutions under microscope.',
      'Wool is a protein fibre with outer cuticular scales providing felting ability and thermal warmth.',
      'Polyester (PET) is synthesized via terephthalic acid & ethylene glycol, exhibiting high crease resistance.'
    ],
    importantTerms: {
      'Monomer': 'Small molecular repeat unit linked to form long polymer chains.',
      'Convolutions': 'Natural ribbon-like twists along the longitudinal axis of mature cotton fibres.',
      'Tenacity': 'Tensile strength of a fibre expressed in grams per denier (g/d) or cN/tex.',
      'Moisture Regain': 'Percentage of moisture absorbed by a bone-dry fibre under standard conditions (65% RH, 20°C).'
    },
    visualExplanation: 'Cross-sectional and longitudinal microscopic diagrams showing cotton kidney bean shape, wool round scaly shape, and polyester smooth cylindrical rod.',
    industrialRelevance: 'Essential for raw material procurement, blend ratio calculation, and yarn spinning parameter adjustments.',
    quickRevisionSummary: 'Natural vs Synthetic classification, cellulose vs protein chemistry, moisture regain (Cotton: 8.5%, Wool: 15-17%, Polyester: 0.4%).'
  },
  {
    topicId: 'bvtd112_t1',
    subjectCode: 'BVTD112',
    unitNumber: 1,
    unitTitle: 'Unit 1: Sewing Machinery & Seam Engineering',
    title: 'Industrial Lockstitch Sewing Machine Anatomy & Seam Construction',
    overview: 'Operation of Single Needle Lockstitch (SNLS) machines, stitch type 301, thread tensioning, and seam assembly.',
    keyPoints: [
      'Stitch Type 301 (SNLS) interlocks upper needle thread and lower bobbin thread in the center of fabric plies.',
      'French Seam encases raw allowances inside a second row of stitching, ideal for sheer fabrics.',
      'Run & Fell (Flat-Felled) seam provides double structural topstitching for denim and workwear.'
    ],
    importantTerms: {
      'Take-Up Lever': 'Mechanical lever pulling thread from spool and tightening interlocked stitch knot.',
      'Feed Dog': 'Serrated teeth advancing fabric by one stitch length per cycle.',
      'SPI': 'Stitches Per Inch, measuring seam density (standard 10-12 SPI for shirting).'
    },
    visualExplanation: 'Schematic of SNLS thread path, rotary hook timing, and French vs Flat-Felled seam cross-sections.',
    industrialRelevance: 'Core practical skill required for garment line supervisors and sample room technicians.'
  },
  {
    topicId: 'bvtd113_u1_t1',
    subjectCode: 'BVTD113',
    unitNumber: 1,
    unitTitle: 'Unit 1: Fundamentals of Entrepreneurship',
    title: 'Entrepreneurial Concepts & Apparel Business Opportunities',
    overview: 'Theoretical framework of innovation by Schumpeter, risk assessment, and market identification in apparel.',
    keyPoints: [
      'Schumpeter defines the entrepreneur as an innovator introducing new products or production methods.',
      'Apparel startups require market gap analysis, capital allocation, and supply chain management.'
    ],
    importantTerms: {
      'DPR': 'Detailed Project Report evaluated by financial institutions for bank loans.',
      'MSME': 'Micro, Small, and Medium Enterprises regulated by the MSMED Act.'
    }
  },
  {
    topicId: 'bvtd121_u1_t1',
    subjectCode: 'BVTD121',
    unitNumber: 1,
    unitTitle: 'Unit 1: Fashion Cycle & Consumer Behavior',
    title: 'The 5 Stages of the Fashion Life Cycle',
    overview: 'In-depth study of Introduction, Rise, Culmination/Peak, Decline, and Obsolescence stages.',
    keyPoints: [
      'Introduction: Avant-garde designs at highest price point in limited quantities.',
      'Rise: Accepted by trend leaders; mass market adaptations begin.',
      'Culmination (Peak): Mass production and maximum popularity at accessible price points.',
      'Decline & Obsolescence: Saturation, markdowns, and replacement by new cycle.'
    ],
    importantTerms: {
      'Fad': 'Short-lived craze characterized by rapid spike and sudden disappearance.',
      'Classic': 'Style maintaining long-term acceptance across decades with minor updates.'
    }
  },
  {
    topicId: 'bvtd122_t1',
    subjectCode: 'BVTD122',
    unitNumber: 1,
    unitTitle: 'Unit 1: Pattern Drafting & Bodice Block',
    title: 'Foundation Pattern Drafting & Bust Apex Pivoting',
    overview: 'Principles of flat pattern drafting, dress form measuring, and dart rotation around the bust apex.',
    keyPoints: [
      'Bust Apex is the pivotal center mound for all front bodice dart suppression changes.',
      'Slash-and-Spread and Pivot methods allow moving waist darts to shoulder or armscye cleanly.'
    ],
    importantTerms: {
      'Apex': 'Highest mound point around which bust darts rotate.',
      'French Curve': 'Clear acrylic template tool for drawing armscye and neckline contours.'
    }
  },
  {
    topicId: 'bvtd123_t1',
    subjectCode: 'BVTD123',
    unitNumber: 1,
    unitTitle: 'Unit 1: Weave Construction & Point Paper Graphing',
    title: 'Basic Weave Interlacement: Plain, Twill, Satin',
    overview: 'Graph representation of 1/1 Plain, 2/2 Twill, and 5-end Satin weaves on point paper.',
    keyPoints: [
      'Plain Weave (1/1): Maximum interlacements, durable, reversible.',
      'Twill Weave (2/2): Prominent diagonal wales at 45° angle.',
      'Point Paper Convention: Marked/filled square = Warp float (Warp over Weft).'
    ],
    importantTerms: {
      'Warp Float': 'Warp yarn passing over one or more weft picks on fabric surface.',
      'Heald Shaft': 'Frame holding heald wires that separate warp threads into shedding layers.'
    }
  }
];

export const INITIAL_PRACTICALS = [
  {
    practicalId: 'bvtd112_p1_color_wheel',
    subjectCode: 'BVTD112',
    title: '12-Hue Color Wheel & Prang Color System',
    objective: 'To construct a standard 12-hue color wheel depicting primary, secondary, and tertiary hues using poster colors.',
    materialsRequired: ['A3 Cartridge sheet', 'Poster colors', 'Synthetic brushes (2, 4, 6)', 'Compass & Protractor'],
    theory: 'Prang color system categorizes hues based on pigment mixtures.',
    stepByStepProcedure: [
      'Draw 20cm diameter circle divided into 12 equal 30-degree sectors.',
      'Paint primary hues (Red, Yellow, Blue) in equidistant sectors.',
      'Mix primaries to form secondary hues (Orange, Green, Violet).',
      'Mix primary + secondary to form 6 tertiary hues.'
    ],
    expectedObservations: 'Clean, opaque color swatches with smooth transitions.',
    precautions: ['Use clean brushes between mixtures', 'Maintain proper paint consistency'],
    vivaQuestions: [
      { question: 'What are the three primary pigment colors?', answer: 'Red, Yellow, Blue.' },
      { question: 'What is a secondary color?', answer: 'Color produced by mixing two equal primary colors (Orange, Green, Violet).' }
    ]
  },
  {
    practicalId: 'bvtd112_p2_burning_test',
    subjectCode: 'BVTD112',
    title: 'Microscopic & Burning Test Fibre Identification',
    objective: 'To identify unknown fibre swatches using burning characteristics and longitudinal microscopic examination.',
    materialsRequired: ['Spirit lamp', 'Tweezers', 'Glass slides', 'Compound microscope', 'Fibre samples'],
    theory: 'Combustion behavior and surface morphology uniquely identify textile fibres.',
    stepByStepProcedure: [
      'Examine fibre surface under 100x microscope.',
      'Hold fibre swatch with tweezers and approach flame edge.',
      'Note odor, flame color, self-extinguishing nature, and residue ash.'
    ],
    expectedObservations: 'Cotton shows convolutions; wool shows scales; synthetic melts into hard bead.',
    precautions: ['Perform burning test over safety tray', 'Do not inhale smoke directly'],
    vivaQuestions: [
      { question: 'How does cotton behave in flame?', answer: 'Burns rapidly with yellow flame, paper burning odor, light feathery ash.' },
      { question: 'How do you identify wool by burning?', answer: 'Burns slowly, self-extinguishes, burning hair odor, dark crushable bead.' }
    ]
  }
];

export const INITIAL_QUIZZES = [
  {
    questionId: 'bvtd111_u1_q1',
    subjectCode: 'BVTD111',
    unitNumber: 1,
    topicId: 'bvtd111_u1_t1',
    question: 'Which natural vegetable fibre is extracted from the seed pod of the Gossypium plant?',
    options: ['Flax / Linen', 'Cotton', 'Jute', 'Hemp'],
    correctIndex: 1,
    explanation: 'Cotton is a natural unicellular vegetable seed-hair fibre composed primarily of cellulose (88-96%).',
    difficulty: 'EASY'
  },
  {
    questionId: 'bvtd111_u1_q2',
    subjectCode: 'BVTD111',
    unitNumber: 1,
    topicId: 'bvtd111_u1_t1',
    question: 'During the burning test, which fibre burns with a smell of burning feathers/hair and leaves a dark, crushable bead?',
    options: ['Cotton', 'Wool', 'Nylon', 'Polyester'],
    correctIndex: 1,
    explanation: 'Wool is a natural animal protein fibre containing keratin and sulfur, giving off a characteristic burning hair odor with irregular, friable ash.',
    difficulty: 'MEDIUM'
  },
  {
    questionId: 'bvtd112_u1_q1',
    subjectCode: 'BVTD112',
    unitNumber: 1,
    topicId: 'bvtd112_t1',
    question: 'What is the ISO 4915 numerical stitch designation for the standard Single Needle Lockstitch (SNLS)?',
    options: ['Stitch Type 101', 'Stitch Type 301', 'Stitch Type 401', 'Stitch Type 504'],
    correctIndex: 1,
    explanation: 'Stitch 301 is the plain lockstitch, formed with two threads (needle thread and bobbin thread) interlacing in the center of the fabric plies.',
    difficulty: 'EASY',
    isPracticalViva: true
  },
  {
    questionId: 'bvtd121_u1_q1',
    subjectCode: 'BVTD121',
    unitNumber: 1,
    topicId: 'bvtd121_u1_t1',
    question: 'What is the chronological sequence of the 5 stages in the Fashion Life Cycle curve?',
    options: [
      'Rise -> Introduction -> Culmination -> Decline -> Obsolescence',
      'Introduction -> Rise -> Culmination (Peak) -> Decline -> Obsolescence',
      'Creation -> Adoption -> Peak -> Reproduction -> Discard',
      'Introduction -> Plateau -> Rise -> Clearance -> Discontinuation'
    ],
    correctIndex: 1,
    explanation: 'The standard fashion cycle follows the 5 stages: Introduction -> Rise -> Culmination/Peak -> Decline -> Obsolescence.',
    difficulty: 'EASY'
  },
  {
    questionId: 'bvtd123_u1_q1',
    subjectCode: 'BVTD123',
    unitNumber: 1,
    topicId: 'bvtd123_t1',
    question: 'What is the simplest fundamental weave structure characterized by an alternate 1/1 over one, under one interlacement?',
    options: ['Plain Weave (Tabby)', 'Twill Weave', 'Satin Weave', 'Jacquard Weave'],
    correctIndex: 0,
    explanation: 'Plain weave (1/1) has the maximum number of yarn interlacements per unit area, giving high structural stability.',
    difficulty: 'EASY',
    isPracticalViva: true
  }
];

export const INITIAL_FLASHCARDS = [
  {
    cardId: 'fc_bvtd112_1',
    subjectCode: 'BVTD112',
    unitNumber: 1,
    topicId: 'bvtd112_t1',
    type: 'IDENTIFICATION',
    front: 'What is a Bobbin & Bobbin Case in an Industrial SNLS Machine?',
    back: 'The bobbin holds the lower underthread. The bobbin case encases the bobbin, controls bottom thread tension with an adjustable leaf spring screw, and fits inside the rotary hook assembly.',
    categoryHint: 'Machine Anatomy & Tools',
    practicalTag: 'Equipment'
  },
  {
    cardId: 'fc_bvtd112_2',
    subjectCode: 'BVTD112',
    unitNumber: 1,
    topicId: 'bvtd112_t1',
    type: 'PRACTICAL',
    front: 'How do you solve Upper Thread Breakage during high-speed sewing?',
    back: '1. Check if needle is blunt or inserted backwards.\n2. Loosen upper tension discs.\n3. Verify thread is threaded through take-up lever.\n4. Check for burrs on throat plate.',
    categoryHint: 'Troubleshooting & Maintenance',
    practicalTag: 'Troubleshooting'
  },
  {
    cardId: 'fc_bvtd111_1',
    subjectCode: 'BVTD111',
    unitNumber: 1,
    topicId: 'bvtd111_u1_t1',
    type: 'DEFINITION',
    front: 'What is a Textile Fibre?',
    back: 'A unit of matter characterized by flexibility, fineness, and a high ratio of length to thickness (at least 100:1), capable of being spun into yarn and woven/knitted into fabric.',
    categoryHint: 'Fundamentals',
    practicalTag: 'Theory'
  },
  {
    cardId: 'fc_bvtd121_1',
    subjectCode: 'BVTD121',
    unitNumber: 1,
    topicId: 'bvtd121_u1_t1',
    type: 'DEFINITION',
    front: "Differentiate between a 'Classic' and a 'Fad'",
    back: 'Classic: Enduring consumer acceptance over decades (e.g. Blazer, Blue Jeans).\nFad: Short-lived craze spiking rapidly and disappearing within weeks.',
    categoryHint: 'Fashion Theory',
    practicalTag: 'Terminology'
  }
];

export const INITIAL_CAREERS = [
  {
    title: 'Apparel Merchandiser',
    sector: 'Export House & Apparel Manufacturing',
    description: 'Acts as vital bridge between buyer and factory, managing BOM, tech packs, fabric approvals, SAM costing, and shipment schedules.',
    keySkills: ['Costing & Pricing', 'BOM & Tech Pack Analysis', 'TNA (Time & Action Calendar)', 'AQL Quality Standards'],
    standardTools: ['FastReact', 'Microsoft Excel', 'TUKAcad', 'Lectra System'],
    industryScope: 'Extremely high demand across Ludhiana, Tirupur, Surat, and Gurgaon apparel hubs.'
  },
  {
    title: 'Textile QA / Testing Chemist',
    sector: 'Textile Testing Laboratories & Quality Inspection',
    description: 'Conducts physical, chemical, microscopic, and fastness testing on raw fibres, yarns, and finished fabrics per ISO/AATCC standards.',
    keySkills: ['Fibre Analysis', 'GSM & Count Determination', 'Spectrophotometry', 'Color Fastness Testing'],
    standardTools: ['DataColor Spectrophotometer', 'Instron Tensile Tester', 'Crockmeter', 'Laundrometer'],
    industryScope: 'Core requirement in NABL accredited labs (SGS, Intertek, Bureau Veritas).'
  }
];

export const INITIAL_RESOURCES = [
  {
    resourceId: 'res_bvtd111_book1',
    title: 'Textile Science — Principles & Polymers',
    subjectCode: 'BVTD111',
    category: 'Textbook',
    description: 'Prescribed reference book for fibre physical chemistry and polymer structure.',
    downloadSize: '14.2 MB',
    format: 'PDF Digital Manual'
  },
  {
    resourceId: 'res_bvtd112_manual',
    title: 'Industrial Sewing Machine Operations Manual',
    subjectCode: 'BVTD112',
    category: 'Manual',
    description: 'Complete laboratory handbook on SNLS operation, maintenance, and seam engineering.',
    downloadSize: '8.7 MB',
    format: 'PDF Lab Guide'
  }
];
