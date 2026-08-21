export const INITIAL_ACADEMIC_SCHEME = {
  degree: 'Bachelor of Vocation (B.Voc.) in Textile Design & Apparel Technology',
  department: 'P.G. Department of Fashion Designing (Session 2026-27)',
  years: [
    {
      yearNumber: 1,
      title: '1st Year',
      status: 'AVAILABLE',
      semesters: [
        {
          number: 1,
          title: 'Semester 1',
          status: 'AVAILABLE',
          totalCredits: 25,
          totalMarks: 600,
          totalHoursPerWeek: 30
        },
        {
          number: 2,
          title: 'Semester 2',
          status: 'AVAILABLE',
          totalCredits: 29,
          totalMarks: 700,
          totalHoursPerWeek: 40
        }
      ]
    },
    {
      yearNumber: 2,
      title: '2nd Year',
      status: 'COMING_SOON',
      semesters: [
        { number: 3, title: 'Semester 3', status: 'COMING_SOON', totalCredits: 0, totalMarks: 0, totalHoursPerWeek: 0 },
        { number: 4, title: 'Semester 4', status: 'COMING_SOON', totalCredits: 0, totalMarks: 0, totalHoursPerWeek: 0 }
      ]
    },
    {
      yearNumber: 3,
      title: '3rd Year',
      status: 'COMING_SOON',
      semesters: [
        { number: 5, title: 'Semester 5', status: 'COMING_SOON', totalCredits: 0, totalMarks: 0, totalHoursPerWeek: 0 },
        { number: 6, title: 'Semester 6', status: 'COMING_SOON', totalCredits: 0, totalMarks: 0, totalHoursPerWeek: 0 }
      ]
    }
  ]
};

export const INITIAL_SUBJECTS = [
  // SEMESTER 1
  {
    code: 'BVTD111',
    name: 'Design Foundation & Basics of Textile',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'THEORY',
    hoursPerWeek: 1,
    lectureCredits: 2,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 2,
    theoryMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusPageRef: 'Page 10',
    overview: 'Fundamental study of design elements, principles, textile fibres, polymer properties, and basic textile classification.',
    courseObjectives: ['Understand basic design principles', 'Classify textile fibres by origin and chemistry', 'Analyze yarn and polymer structures'],
    booksPrescribed: ['Textile Science by E.P. Gohl & L.D. Vilensky', 'Understanding Textiles by Phyllis G. Tortora'],
    instructionsForPaperSetters: 'The examiner will set three questions from Section-I, each question carrying 12 marks. Section A carries 09 marks (compulsory short questions).'
  },
  {
    code: 'BVTD112',
    name: 'Design Foundation & Basics of Textile (practical)',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 4,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 2,
    totalCredits: 2,
    practicalMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusPageRef: 'Page 11',
    overview: 'Laboratory practicals on microscopic fibre identification, burning test, chemical solubility, and basic swatching.',
    courseObjectives: ['Perform microscopic & chemical fibre identification', 'Analyze color swatches and textile textures']
  },
  {
    code: 'BVTD113',
    name: 'Sewing Techniques (practical)',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 8,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 4,
    totalCredits: 4,
    practicalMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 11 & 12',
    overview: 'Hands-on practical training in sewing machine parts, maintenance, basic stitching, seam construction, fullness, plackets, pockets, sleeves, and collars.',
    courseObjectives: ['To impart knowledge of sewing techniques', 'To apply the knowledge for basic stitching'],
    courseOutcomes: [
      'COS1: Explain about the various components of garment construction and its application.',
      'COS2: To gain knowledge in seams and seam finishes.',
      'COS3: To enable the students a basic garment construction details.'
    ],
    booksPrescribed: [
      'The Art of couture sewing by Zoya Nudelman (Bloomsbury Academic)',
      'The sewing Book by Alison Smith',
      'Sewing Techniques and Patterns by Marie-Naelle Bayard',
      'Complete Guide to Sewing by Reader\'s Digest',
      'The ultimate sewing book by Maggi McCormick'
    ],
    instructionsForPaperSetters: 'The examiner will set three questions from Section-I, each question carrying 12 marks.'
  },
  {
    code: 'BVTD114',
    name: 'Introduction to Enterprenurship',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MINOR',
    type: 'THEORY',
    hoursPerWeek: 4,
    lectureCredits: 4,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 4,
    theoryMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 13-14',
    overview: 'Comprehensive study of entrepreneurship concepts, functions, development processes, support systems, and enterprise barriers.',
    courseObjectives: ['To impart the knowledge of Entrepreneurship - which will help the students to start their enterprise.'],
    booksPrescribed: [
      'Entrepreneurial Development by Dr. S. Moharana and Dr. Dash (RBSA Publishers, Jaipur)',
      'Entrepreneurial Development by S.S. Khanna (S. Chand & Company Ltd.)',
      'Entrepreneurial Development by C.B. Gupta and N.P. Srinivasan (Sultan Chand & Co.)'
    ],
    instructionsForPaperSetters: 'There will be five sections. Section A carries 09 marks (compulsory short questions). Sections B, C, D and E will be set from Unit I, II, III, & IV respectively consisting of two questions of 7 marks each.'
  },
  {
    code: 'CS-BVTD111',
    name: 'Computer Application-I',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MINOR',
    type: 'THEORY_AND_PRACTICAL',
    hoursPerWeek: 6,
    lectureCredits: 2,
    tutorialCredits: 0,
    practicalCredits: 2,
    totalCredits: 4,
    theoryMarks: 50,
    practicalMarks: 25,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 15-16',
    overview: 'Fundamentals of computer systems, operating environments, MS Office applications, and digital layout basics for textile design.'
  },
  {
    code: 'BCSV-1129',
    name: 'Communication Skills in English-I',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'ABILITY_ENHANCEMENT',
    type: 'THEORY_AND_PRACTICAL',
    hoursPerWeek: 3,
    lectureCredits: 3,
    tutorialCredits: 0,
    practicalCredits: 1,
    totalCredits: 4,
    theoryMarks: 60,
    practicalMarks: 15,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 17-19',
    overview: 'Essential English reading, writing, listening, and oral communication skills for professional fashion environments.'
  },
  {
    code: 'BHPB-1101',
    name: 'Punjabi (compulsory) / Basic Punjabi / Punjab History & Culture',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'ABILITY_ENHANCEMENT',
    type: 'THEORY',
    hoursPerWeek: 4,
    lectureCredits: 4,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 4,
    theoryMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 20-24',
    overview: 'Compulsory language study or regional history & culture (BHPB-1101 / BPBI-1102 / BPHC-1104).'
  },
  {
    code: 'ZDA111',
    name: 'Drug Abuse: Problems, Management and Prevention (compulsory)',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'VALUE_ADDED',
    type: 'THEORY',
    hoursPerWeek: 1,
    lectureCredits: 1,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 1,
    theoryMarks: 0,
    internalAssessmentMarks: 25,
    totalMarks: 25,
    syllabusPageRef: 'Page 25-27',
    overview: 'Compulsory value-added course addressing social awareness, health management, and preventive education.'
  },

  // SEMESTER 2
  {
    code: 'BVTD121',
    name: 'Introduction to Fashion',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
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
    syllabusPageRef: 'Page 28-29',
    overview: 'Study of fashion terminologies, fashion life cycle curve, theories of fashion adoption, and trend forecasting.',
    courseObjectives: ['Analyze fashion cycles & fad patterns', 'Master fashion adoption theories', 'Evaluate consumer buying behavior']
  },
  {
    code: 'BVTD122',
    name: 'Garment sewing (practical)',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 8,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 4,
    totalCredits: 4,
    practicalMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 30',
    overview: 'Advanced garment assembly, adult garment construction, fitting, dart suppression, and finishings.'
  },
  {
    code: 'BVTD123',
    name: 'Design foundation and basics of textiles - II (Practical)',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 8,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 4,
    totalCredits: 4,
    practicalMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 31',
    overview: 'Woven fabric analysis (Plain, Twill, Satin), point paper design representation, reed-pick calculations, and GSM analysis.'
  },
  {
    code: 'BVTD124',
    name: 'Enterprise Planning',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MINOR',
    type: 'THEORY',
    hoursPerWeek: 4,
    lectureCredits: 4,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 4,
    theoryMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 32-33',
    overview: 'Business planning, financial project appraisal, Detailed Project Report (DPR) drafting, and MSME regulatory policies.'
  },
  {
    code: 'CS-BVTD121',
    name: 'Computer Applications-II (Practical)',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MINOR',
    type: 'PRACTICAL',
    hoursPerWeek: 8,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 4,
    totalCredits: 4,
    practicalMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 34-35',
    overview: 'CAD applications for garment pattern drafting, digital croquis rendering, and fabric motif creation.'
  },
  {
    code: 'BCSV-1229',
    name: 'Communication Skills in English-II',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'ABILITY_ENHANCEMENT',
    type: 'THEORY_AND_PRACTICAL',
    hoursPerWeek: 3,
    lectureCredits: 3,
    tutorialCredits: 0,
    practicalCredits: 1,
    totalCredits: 4,
    theoryMarks: 60,
    practicalMarks: 15,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 36-38',
    overview: 'Advanced business communication, report writing, presentation skills, and professional correspondence.'
  },
  {
    code: 'BHPB-1201',
    name: 'Punjabi (compulsory) / Basic Punjabi / Punjab History & Culture',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'ABILITY_ENHANCEMENT',
    type: 'THEORY',
    hoursPerWeek: 4,
    lectureCredits: 4,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 4,
    theoryMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusPageRef: 'Page 39-43',
    overview: 'Compulsory language study or regional history & culture (BHPB-1201 / BPBI-1202 / BPHC-1204).'
  },
  {
    code: 'ZDA121',
    name: 'Drug Abuse: Problems, Management and Prevention (compulsory)',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'VALUE_ADDED',
    type: 'THEORY',
    hoursPerWeek: 1,
    lectureCredits: 1,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 1,
    theoryMarks: 0,
    internalAssessmentMarks: 25,
    totalMarks: 25,
    syllabusPageRef: 'Page 44-45',
    overview: 'Compulsory value-added course addressing preventive health education and social responsibility.'
  }
];

export const INITIAL_TOPICS = [
  // BVTD111 - Design Foundation & Basics of Textile
  {
    topicId: 'bvtd111_u1_t1',
    subjectCode: 'BVTD111',
    unitNumber: 1,
    unitTitle: 'Unit-I: Elements of Design & Fibre Classification',
    title: 'Fibre Classification & Polymer Structure',
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

  // BVTD112 - Design Foundation (Practical)
  {
    topicId: 'bvtd112_sec1_t1',
    subjectCode: 'BVTD112',
    unitNumber: 1,
    unitTitle: 'Section-I: Microscopic & Chemical Fibre Testing',
    title: 'Microscopic & Burning Test Fibre Identification',
    overview: 'Laboratory practicals on microscopic identification, burning test, chemical solubility, and lockstitch sewing machine operations.',
    keyPoints: [
      'Microscopic examination reveals longitudinal convolutions in cotton and scales in wool.',
      'Burning test differentiates protein (burning hair odor) from cellulose (burning paper odor) and synthetics (hard bead).'
    ],
    importantTerms: {
      'Solubility Test': 'Using 70% Sulfuric Acid or Acetone to dissolve specific fibre types chemically.'
    }
  },

  // BVTD113 - Sewing Techniques (Practical)
  {
    topicId: 'bvtd113_sec1_t1',
    subjectCode: 'BVTD113',
    unitNumber: 1,
    unitTitle: 'Section-I: Sewing Machine & Stitching Fundamentals',
    title: 'Parts of Sewing Machine, Care & Maintenance',
    overview: 'Comprehensive study of sewing machine components (needle bar, take-up lever, feed dog, presser foot, tension disc, bobbin case) and essential maintenance procedures.',
    keyPoints: [
      'Identification and functions of primary sewing machine components.',
      'Daily cleaning and oiling procedures to prevent friction and thread jamming.',
      'Troubleshooting common machine defects like needle breakage, skipped stitches, and thread tension imbalance.'
    ],
    importantTerms: {
      'Feed Dog': 'Serrated metal teeth beneath the presser foot that move the fabric forward per stitch.',
      'Take-up Lever': 'Lever that feeds thread to needle and draws up slack after loop formation.',
      'Tension Assembly': 'Discs and spring screw regulating upper needle thread tightness.'
    }
  },
  {
    topicId: 'bvtd113_sec1_t2',
    subjectCode: 'BVTD113',
    unitNumber: 1,
    unitTitle: 'Section-I: Sewing Machine & Stitching Fundamentals',
    title: 'Basic Hand Sewing Techniques',
    overview: 'Temporary and permanent hand stitches including basting, running stitch, backstitch, hemming, and catch stitch.',
    keyPoints: [
      'Temporary stitches (even basting, uneven basting, diagonal basting) for fabric alignment before machine stitching.',
      'Permanent hand stitches (backstitch, stem stitch, blanket stitch) for decorative and utility finishing.'
    ],
    importantTerms: {
      'Basting': 'Long temporary stitches used to hold fabric layers in place before final sewing.',
      'Blind Hemming': 'Subtle stitch catching a single thread of outer fabric to secure hem invisibly.'
    }
  },
  {
    topicId: 'bvtd113_sec1_t3',
    subjectCode: 'BVTD113',
    unitNumber: 1,
    unitTitle: 'Section-I: Sewing Machine & Stitching Fundamentals',
    title: 'Seams and Seam Finishes',
    overview: 'Construction of structural seams: Plain seam, Run and Fell seam, French seam, Counter seam, and pinked/overlocked finishes.',
    keyPoints: [
      'Plain Seam: Standard join formed by placing right sides together and stitching at designated seam allowance.',
      'French Seam: Self-enclosed double stitch seam ideal for sheer and lightweight delicate fabrics.',
      'Run and Fell Seam: Heavy-duty flat-felled seam with two visible topstitching lines used in denim and tailored garments.'
    ],
    importantTerms: {
      'Seam Allowance': 'Distance between fabric edge and line of stitching (standard 5/8 inch or 1.5 cm).',
      'French Seam': 'Seam where raw allowances are enclosed inside a folded row of stitching.'
    }
  },
  {
    topicId: 'bvtd113_sec1_t4',
    subjectCode: 'BVTD113',
    unitNumber: 1,
    unitTitle: 'Section-I: Sewing Machine & Stitching Fundamentals',
    title: 'Fullness Controls: Yokes, Gathers, Darts & Pleats',
    overview: 'Methods for managing volume and 3D shaping in garment construction.',
    keyPoints: [
      'Darts: Tapered folds stitched to contour flat fabric over body curves (bust, waist, hip).',
      'Pleats: Crisp structural fabric folds (box pleats, knife pleats, inverted pleats).',
      'Gathers: Even distribution of fabric fullness drawn together using two parallel basting rows.'
    ],
    importantTerms: {
      'Dart Point': 'The sharp vanishing apex point of a dart fold.',
      'Box Pleat': 'Two knife pleats folded in opposite directions facing away from each other.'
    }
  },
  {
    topicId: 'bvtd113_sec1_t5',
    subjectCode: 'BVTD113',
    unitNumber: 1,
    unitTitle: 'Section-I: Sewing Machine & Stitching Fundamentals',
    title: 'Plackets & Pockets',
    overview: 'Construction of garment openings (French placket, continuous placket, extended placket) and functional pockets (patch, welt, in-seam, kurta).',
    keyPoints: [
      'Plackets allow ease of dressing while maintaining structured closure alignment.',
      'Patch Pockets: Surface mounted pockets stitched directly onto garment exterior.',
      'Welt Pockets: Tailored slit pockets with reinforced fabric lips.'
    ],
    importantTerms: {
      'Continuous Placket': 'Single bias or straight strip used to finish sleeve cuffs or neck slit openings.',
      'Welt': 'Narrow fabric band encasing slit pocket opening.'
    }
  },
  {
    topicId: 'bvtd113_sec1_t6',
    subjectCode: 'BVTD113',
    unitNumber: 1,
    unitTitle: 'Section-I: Sewing Machine & Stitching Fundamentals',
    title: 'Sleeves & Collars',
    overview: 'Drafting and attachment of plain, puff, bishop, bell, cap, raglan, kimono, dolman sleeves and Peter Pan, shawl, mandarin, cape collars.',
    keyPoints: [
      'Set-in Sleeves (Plain, Puff, Bishop) attached into circular armscye.',
      'Raglan & Kimono Sleeves offering continuous shoulder extension.',
      'Collars: Flat (Peter Pan), Standing (Mandarin), and Rolled (Shawl).'
    ],
    importantTerms: {
      'Armscye': 'The armhole seam opening of a bodice block.',
      'Mandarin Collar': 'Short, upright band collar standing close to the neck.'
    }
  },

  // BVTD114 - Introduction to Enterprenurship
  {
    topicId: 'bvtd114_u1_t1',
    subjectCode: 'BVTD114',
    unitNumber: 1,
    unitTitle: 'Unit-I: Entrepreneurship Fundamentals',
    title: 'Entrepreneurship: Concept, Functions and Need',
    overview: 'Core concepts of entrepreneurship, Schumpeterian innovation, economic functions, and national necessity for apparel ventures.',
    keyPoints: [
      'Definition of entrepreneurship as risk-bearing innovation and resource assembly.',
      'Primary functions: opportunity identification, decision making, capital formation, market creation.',
      'Need for entrepreneurship in textile and garment manufacturing to drive employment and exports.'
    ],
    importantTerms: {
      'Entrepreneur': 'An individual who organizes, operates, and assumes the financial risks of a business venture.',
      'Innovation': 'Introduction of a new product, method, market, or raw material source.'
    }
  },
  {
    topicId: 'bvtd114_u2_t1',
    subjectCode: 'BVTD114',
    unitNumber: 2,
    unitTitle: 'Unit-II: Entrepreneurship Characteristics & Process',
    title: 'Characteristics of Entrepreneurship & Development Process',
    overview: 'Psychological and managerial traits of successful entrepreneurs and the sequential process of enterprise building.',
    keyPoints: [
      'Key traits: Need for achievement, high risk tolerance, self-confidence, vision, perseverance.',
      'Entrepreneurship Development Process: Idea generation -> Feasibility analysis -> Resource mobilization -> Startup execution -> Growth management.'
    ],
    importantTerms: {
      'Feasibility Study': 'Evaluation of practical, financial, and technical viability of a proposed project.',
      'Risk Tolerance': 'Capacity to endure uncertainty and financial exposure.'
    }
  },
  {
    topicId: 'bvtd114_u3_t1',
    subjectCode: 'BVTD114',
    unitNumber: 3,
    unitTitle: 'Unit-III: Institutional Support',
    title: 'Help and Support to Entrepreneurs',
    overview: 'Institutional frameworks, government MSME schemes, SIDBI, DIC, NSIC, and financial subsidies for apparel enterprises.',
    keyPoints: [
      'District Industries Centre (DIC) providing single-window clearances.',
      'Micro, Small & Medium Enterprises (MSME) policies and credit guarantee schemes.',
      'Subsidies for machinery modernization, textile technology upgrades (TUFS), and incubators.'
    ],
    importantTerms: {
      'DIC': 'District Industries Centre providing guidance and registration for MSMEs.',
      'DPR': 'Detailed Project Report submitted to banks for term loans.'
    }
  },
  {
    topicId: 'bvtd114_u4_t1',
    subjectCode: 'BVTD114',
    unitNumber: 4,
    unitTitle: 'Unit-IV: Entrepreneurial Challenges',
    title: 'Barriers to Entrepreneurship',
    overview: 'Environmental, financial, social, cultural, administrative, and technological obstacles in starting ventures.',
    keyPoints: [
      'Financial barriers: Inadequate initial capital, high interest rates, lack of collateral.',
      'Administrative barriers: Complex regulatory compliance, red tape, delayed clearances.',
      'Market barriers: Intense competition, supply chain disruptions, changing fashion trends.'
    ],
    importantTerms: {
      'Collateral': 'Asset pledged by a borrower as security for loan repayment.',
      'Red Tape': 'Excessive administrative regulation or rigid conformity to formal rules.'
    }
  },

  // CS-BVTD111 - Computer Application-I
  {
    topicId: 'cs_bvtd111_u1_t1',
    subjectCode: 'CS-BVTD111',
    unitNumber: 1,
    unitTitle: 'Unit-I: Computer Systems & OS Environment',
    title: 'PC Anatomy & Operating Systems',
    overview: 'Introduction to hardware components, operating system fundamentals, file management, and digital peripherals for textile studios.',
    keyPoints: [
      'Hardware vs Software architecture.',
      'File system hierarchy, directory navigation, and backup safety.'
    ],
    importantTerms: {
      'OS': 'Operating System managing software resources and hardware peripherals.'
    }
  },

  // BCSV-1129 - Communication Skills in English-I
  {
    topicId: 'bcsv_1129_u1_t1',
    subjectCode: 'BCSV-1129',
    unitNumber: 1,
    unitTitle: 'Unit-I: Business Communication & Vocabulary',
    title: 'Professional Vocabulary & Reading Comprehension',
    overview: 'Building professional English vocabulary, reading strategies, and business correspondence fundamentals.',
    keyPoints: [
      'Active vs Passive voice in formal correspondence.',
      'Structuring clear business letters and professional emails.'
    ],
    importantTerms: {
      'Salutation': 'Formal greeting line in business letter correspondence.'
    }
  },

  // BVTD121 - Introduction to Fashion
  {
    topicId: 'bvtd121_u1_t1',
    subjectCode: 'BVTD121',
    unitNumber: 1,
    unitTitle: 'Unit-I: Fashion Fundamentals',
    title: 'Fashion Terminologies & The 5-Stage Fashion Cycle',
    overview: 'Comprehensive study of fashion terminologies (style, design, fashion, trend, fad, classic) and the 5 stages of the fashion life cycle.',
    keyPoints: [
      'Fashion cycle stages: Introduction -> Rise -> Culmination/Peak -> Decline -> Obsolescence.',
      'Differences between short-lived fads, mainstream fashions, and enduring classics.'
    ],
    importantTerms: {
      'Fad': 'Short-lived craze characterized by rapid spike and sudden disappearance.',
      'Classic': 'Style maintaining long-term acceptance across decades with minor updates.'
    }
  },

  // BVTD122 - Garment Sewing (Practical)
  {
    topicId: 'bvtd122_sec1_t1',
    subjectCode: 'BVTD122',
    unitNumber: 1,
    unitTitle: 'Section-I: Adult Bodice Block & Pattern Construction',
    title: '8-Head Croquis & Bodice Block Creation',
    overview: 'Pattern drafting, 8-head croquis figure sketching, bodice block creation, and dart manipulation.',
    keyPoints: [
      'Bust apex pivoting for front bodice darts.',
      'Drafting basic skirt foundation block and shoulder dart transfers.'
    ],
    importantTerms: {
      'Apex': 'Highest mound point around which bust darts rotate.'
    }
  },

  // BVTD123 - Design Foundation & Basics of Textiles II (Practical)
  {
    topicId: 'bvtd123_sec1_t1',
    subjectCode: 'BVTD123',
    unitNumber: 1,
    unitTitle: 'Section-I: Woven Fabric Structures & Graph Analysis',
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
  },

  // BVTD124 - Enterprise Planning
  {
    topicId: 'bvtd124_u1_t1',
    subjectCode: 'BVTD124',
    unitNumber: 1,
    unitTitle: 'Unit-I: Business Planning & DPR Appraisal',
    title: 'Detailed Project Report (DPR) Formulation',
    overview: 'Comprehensive process of formulating a DPR for garment manufacturing units, financial appraisal, and MSME capital subsidies.',
    keyPoints: [
      'Project identification, capacity planning, machinery selection.',
      'Break-even point (BEP) analysis and payback period calculation.'
    ],
    importantTerms: {
      'BEP': 'Break-even Point where total revenue equals total fixed and variable costs.'
    }
  }
];

export const INITIAL_PRACTICALS = [
  {
    practicalId: 'bvtd113_p1_seams',
    subjectCode: 'BVTD113',
    title: 'Seam & Seam Finish Construction',
    objective: 'To construct plain, French, run-and-fell, and counter seams on woven cotton fabric swatches.',
    materialsRequired: ['Cotton fabric swatches (15cm x 20cm)', 'Sewing thread', 'Lockstitch sewing machine', 'Ironing station', 'Scissors'],
    theory: 'Seams form the primary structural bond joining garment pieces. Finishing prevents raw edge fraying.',
    stepByStepProcedure: [
      'Plain Seam: Place swatches right sides together, stitch at 1.5cm allowance, press open.',
      'French Seam: Place swatches wrong sides together, stitch at 0.5cm, trim, turn right sides together, stitch at 0.75cm encasing raw edges.',
      'Run & Fell Seam: Stitch plain seam at 1.5cm, trim one allowance by half, fold wider allowance over trimmed edge, topstitch flat.'
    ],
    expectedObservations: 'Clean, flat seam lines without puckering. French seam completely encasing raw fibers.',
    precautions: ['Maintain consistent presser foot alignment', 'Press seams immediately after stitching'],
    vivaQuestions: [
      { question: 'What is the main advantage of a French seam?', answer: 'It completely encloses raw fabric edges, making it neat and durable for sheer or delicate fabrics.' },
      { question: 'Which seam is commonly used on denim jeans?', answer: 'Run and fell (flat-felled) seam for double topstitched structural strength.' }
    ]
  },
  {
    practicalId: 'bvtd113_p2_fullness',
    subjectCode: 'BVTD113',
    title: 'Fullness Control: Darts, Pleats & Gathers',
    objective: 'To construct single-pointed waist darts, knife pleats, box pleats, and gathered panels.',
    materialsRequired: ['Cotton fabric samples', 'Pattern paper', 'Tailor chalk', 'Pins', 'Sewing machine'],
    theory: 'Fullness techniques transform flat 2D fabric into 3D shapes fitting human anatomical contours.',
    stepByStepProcedure: [
      'Draft dart legs on wrong side of fabric converging at dart point.',
      'Fold along centerline right sides together, pin legs, stitch from wide base to vanishing point.',
      'Tie thread tails at vanishing point without backstitching to avoid puckering.'
    ],
    expectedObservations: 'Smooth dart mound without bubble or pouch at the apex.',
    precautions: ['Never press dart point harshly; press over a tailor ham'],
    vivaQuestions: [
      { question: 'Why should you not backstitch at a dart apex?', answer: 'Backstitching at the apex creates an unsightly sharp bump or pucker on the outer garment.' }
    ]
  },
  {
    practicalId: 'bvtd112_p1_microscope',
    subjectCode: 'BVTD112',
    title: 'Microscopic & Burning Test Fibre Identification',
    objective: 'To identify unknown fibre samples using longitudinal microscopic view and flame behavior.',
    materialsRequired: ['Glass slides', 'Compound microscope', 'Spirit lamp', 'Tweezers', 'Cotton, Wool, Silk, Polyester samples'],
    theory: 'Morphology and chemical combustion behavior uniquely identify textile fibres.',
    stepByStepProcedure: [
      'Place fibre on glass slide with water drop, cover with coverslip.',
      'Observe under 100x magnification for convolutions (cotton) or scales (wool).',
      'Perform flame burning test over safety tray.'
    ],
    expectedObservations: 'Cotton shows flat twisted ribbon convolutions; wool shows surface scale overlap.',
    precautions: ['Perform burning test in ventilated area'],
    vivaQuestions: [
      { question: 'How do you identify wool under a microscope?', answer: 'Presence of overlapping surface cuticular scales.' }
    ]
  }
];

export const INITIAL_QUIZZES = [
  {
    questionId: 'bvtd113_q1',
    subjectCode: 'BVTD113',
    unitNumber: 1,
    topicId: 'bvtd113_sec1_t3',
    question: 'Which seam completely encases raw fabric edges inside a folded second row of stitching?',
    options: ['Plain Seam', 'French Seam', 'Lapped Seam', 'Bound Seam'],
    correctIndex: 1,
    explanation: 'French seam is a self-enclosed seam where raw edge allowances are encased inside a folded row of stitching.',
    difficulty: 'EASY',
    isPracticalViva: true
  },
  {
    questionId: 'bvtd114_q1',
    subjectCode: 'BVTD114',
    unitNumber: 1,
    topicId: 'bvtd114_u1_t1',
    question: 'According to Joseph Schumpeter, what is the primary defining function of an entrepreneur?',
    options: ['Capital investment only', 'Innovation', 'Routine administration', 'Manual labor'],
    correctIndex: 1,
    explanation: 'Schumpeter defined the entrepreneur as an innovator who introduces new products, processes, or markets.',
    difficulty: 'EASY'
  },
  {
    questionId: 'bvtd114_q2',
    subjectCode: 'BVTD114',
    unitNumber: 3,
    topicId: 'bvtd114_u3_t1',
    question: 'What does DIC stand for in the context of government support for small-scale enterprises in India?',
    options: ['District Industries Centre', 'Department of Industrial Commerce', 'Direct Investment Council', 'Development Industry Corporation'],
    correctIndex: 0,
    explanation: 'District Industries Centre (DIC) provides single-window clearance, registration, and advisory services for MSMEs.',
    difficulty: 'MEDIUM'
  },
  {
    questionId: 'bvtd111_q1',
    subjectCode: 'BVTD111',
    unitNumber: 1,
    topicId: 'bvtd111_u1_t1',
    question: 'What is the natural percentage of cellulose found in mature cotton fibres?',
    options: ['50-60%', '70-75%', '88-96%', '100%'],
    correctIndex: 2,
    explanation: 'Cotton is a natural cellulosic seed-hair fibre containing approximately 88-96% pure cellulose.',
    difficulty: 'EASY'
  }
];

export const INITIAL_FLASHCARDS = [
  {
    cardId: 'fc_bvtd113_1',
    subjectCode: 'BVTD113',
    unitNumber: 1,
    topicId: 'bvtd113_sec1_t1',
    type: 'IDENTIFICATION',
    front: 'What is the function of the Feed Dog in a sewing machine?',
    back: 'Serrated metal teeth beneath the needle plate that advance the fabric forward by one stitch length during each sewing cycle.',
    categoryHint: 'Machine Parts',
    practicalTag: 'Equipment'
  },
  {
    cardId: 'fc_bvtd114_1',
    subjectCode: 'BVTD114',
    unitNumber: 1,
    topicId: 'bvtd114_u1_t1',
    type: 'DEFINITION',
    front: 'What is a Detailed Project Report (DPR)?',
    back: 'A comprehensive document detailing technical, financial, managerial, and commercial feasibility of a proposed business venture, used by banks to evaluate loan approvals.',
    categoryHint: 'Business Planning',
    practicalTag: 'Theory'
  }
];

export const INITIAL_CAREERS = [
  {
    title: 'Apparel Merchandiser',
    sector: 'Garment Manufacturing & Export Houses',
    description: 'Manages order processing, Tech Pack execution, BOM, sample approvals, TNA calendars, and buyer communication.',
    keySkills: ['Costing & Pricing', 'BOM & Tech Pack Analysis', 'TNA Calendar', 'Quality Inspection (AQL)'],
    standardTools: ['FastReact', 'Microsoft Excel', 'Lectra CAD'],
    industryScope: 'High demand across apparel export hubs in Ludhiana, Tirupur, Gurgaon, and Surat.'
  },
  {
    title: 'Garment Production Supervisor',
    sector: 'Apparel Assembly Lines',
    description: 'Oversees sewing line layout, SAM calculations, machine line balancing, quality control, and operator targets.',
    keySkills: ['Sewing Machine Maintenance', 'Line Balancing', 'SAM & Efficiency Calculation', 'Defect Analysis'],
    standardTools: ['Stopwatch SAM Audit', 'Juki SNLS/Overlock Machines', 'Ergonomic Line Maps'],
    industryScope: 'Core operational leadership role in modern garment manufacturing plants.'
  }
];

export const INITIAL_RESOURCES = [
  {
    resourceId: 'res_bvtd113_sewing',
    title: 'The Art of Couture Sewing (Zoya Nudelman)',
    subjectCode: 'BVTD113',
    category: 'Textbook',
    description: 'Prescribed reference book for advanced stitching, seams, and garment construction details.',
    downloadSize: '18.5 MB',
    format: 'PDF Syllabus Reference'
  },
  {
    resourceId: 'res_bvtd114_ent',
    title: 'Entrepreneurial Development Guide (Dr. S. Moharana)',
    subjectCode: 'BVTD114',
    category: 'Textbook',
    description: 'Prescribed reference book for MSME enterprise planning, institutional support, and DPR formulation.',
    downloadSize: '12.3 MB',
    format: 'PDF Study Manual'
  },
  {
    resourceId: 'res_bvtd111_textile',
    title: 'Textile Science — Principles & Polymers (E.P. Gohl)',
    subjectCode: 'BVTD111',
    category: 'Textbook',
    description: 'Prescribed reference book for fibre chemistry, physical structure, and polymer properties.',
    downloadSize: '14.2 MB',
    format: 'PDF Study Manual'
  }
];
