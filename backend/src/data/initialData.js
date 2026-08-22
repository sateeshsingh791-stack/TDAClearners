export const INITIAL_ACADEMIC_SCHEME = {
  degree: 'Bachelor of Vocation (B.Voc.) in Textile Design & Apparel Technology',
  department: 'P.G. Department of Fashion Designing, Khalsa College Amritsar (An Autonomous College)',
  session: 'Session 2021-22 / 2020-21 (GNDU Autonomous Scheme)',
  years: [
    {
      yearNumber: 1,
      title: '1st Year (Diploma in Textile Design & Apparel Technology)',
      status: 'AVAILABLE',
      semesters: [
        {
          number: 1,
          title: 'Semester 1',
          status: 'AVAILABLE',
          totalCredits: 25,
          totalMarks: 400,
          totalHoursPerWeek: 30
        },
        {
          number: 2,
          title: 'Semester 2',
          status: 'AVAILABLE',
          totalCredits: 29,
          totalMarks: 400,
          totalHoursPerWeek: 40
        }
      ]
    },
    {
      yearNumber: 2,
      title: '2nd Year (Advanced Diploma)',
      status: 'AVAILABLE',
      semesters: [
        { number: 3, title: 'Semester 3', status: 'AVAILABLE', totalCredits: 26, totalMarks: 400, totalHoursPerWeek: 30 },
        { number: 4, title: 'Semester 4', status: 'AVAILABLE', totalCredits: 28, totalMarks: 400, totalHoursPerWeek: 35 }
      ]
    },
    {
      yearNumber: 3,
      title: '3rd Year (B.Voc. Degree)',
      status: 'COMING_SOON',
      semesters: [
        { number: 5, title: 'Semester 5', status: 'COMING_SOON', totalCredits: 0, totalMarks: 0, totalHoursPerWeek: 0 },
        { number: 6, title: 'Semester 6', status: 'COMING_SOON', totalCredits: 0, totalMarks: 0, totalHoursPerWeek: 0 }
      ]
    }
  ]
};

export const INITIAL_SUBJECTS = [
  // SEMESTER 1 (Official Khalsa College Syllabus Page 2 & Pages 4-16)
  {
    code: 'BVTD101',
    aliases: ['BVTD101', 'BVTD 101', 'BCSV-1129'],
    name: 'Communication Skills in English-I',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'ABILITY_ENHANCEMENT',
    type: 'THEORY_AND_PRACTICAL',
    hoursPerWeek: 3,
    lectureCredits: 2,
    tutorialCredits: 0,
    practicalCredits: 1,
    totalCredits: 3,
    theoryMarks: 25,
    practicalMarks: 12,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 4-5',
    overview: 'Reading tactics, comprehension, formal business correspondence, resumes, memos, and oral communication skills.',
    courseObjectives: ['Develop reading tactics and comprehension', 'Master business letter writing and resumes', 'Enhance oral communication skills'],
    booksPrescribed: [
      'Oxford Guide to Effective Writing and Speaking by John Seely',
      'English Grammar in Use (Fourth Edition) by Raymond Murphy, CUP'
    ],
    instructionsForPaperSetters: 'Eight questions of equal marks specified in syllabus to be set, two in each of the four Sections (A-D). Candidates attempt five questions selecting at least one from each Section. (5X5 = 25 Marks).'
  },
  {
    code: 'BVTD102',
    aliases: ['BVTD102', 'BVTD 102', 'BHPB-1101', 'P-BVTD102'],
    name: 'Punjabi (Compulsory) / Basic Punjabi / Punjab History & Culture (Earliest Times to C 320)',
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
    theoryMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 6-7',
    overview: 'Compulsory language study or regional history & culture of Punjab (Physical features, Harappan civilization, Rig Vedic age, Buddhism & Jainism).',
    booksPrescribed: [
      'History and Culture of the Punjab by L. Joshi (ed)',
      'History of Punjab Vol. I by L.M. Joshi & Fauja Singh',
      'Glimpses of Ancient Punjab by Budha Parkash',
      'Life in Northern India by B.N. Sharma'
    ],
    instructionsForPaperSetters: 'The question paper consists of five units: I, II, III, IV and V. Units I-IV will have two questions each (8 marks each). Unit V consists of 7 short answer questions (attempt 5, 1 mark each).'
  },
  {
    code: 'BVTD103',
    aliases: ['BVTD103', 'BVTD 103', 'ZDA111', 'P-BVTD103'],
    name: 'Drug Abuse: Problem, Management and Prevention (Compulsory)',
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
    internalAssessmentMarks: 0,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 8-9',
    overview: 'Compulsory value-added course covering nature of drug abuse, consequences for individual, family, society, nation, and medical/psychiatric management.',
    booksPrescribed: [
      'Social Problems in India by Ram Ahuja (Rawat Publication)',
      'Extent, Pattern and Trend of Drug Use in India (Ministry of Social Justice & Empowerment)',
      'Drug Abuse-Problem, Management & Prevention by Jasjit Kaur Randhawa & Samreet Randhawa'
    ],
    instructionsForPaperSetters: 'Section-A (15 Marks): 5 short answer questions, attempt 3 (5 marks each). Section-B (20 Marks): 4 essay questions, attempt 2 (10 marks each). Section-C (15 Marks): 2 questions, attempt 1.'
  },
  {
    code: 'BVTD104',
    aliases: ['BVTD104', 'BVTD 104', 'CS-BVTD111'],
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
    theoryMarks: 25,
    practicalMarks: 12,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 10-11',
    overview: 'Fundamentals of computer systems, block diagram, generations, data processing, input/output hardware, secondary storage, and MS-Word formatting/mail merge.',
    booksPrescribed: [
      'Fundamentals of Information Technology by V.K. Jain',
      'Introduction to Computers by Peter Norton (McGraw Hill)',
      'Computer Fundamentals by P.K. Sinha'
    ],
    instructionsForPaperSetters: 'Section A: 10 short questions, attempt 7 (1 mark each). Section B: 4 essay questions from Unit-I, attempt 2 (4 marks each). Section C: 4 essay questions from Unit-II, attempt 2 (4 marks each).'
  },
  {
    code: 'BVTD105',
    aliases: ['BVTD105', 'BVTD 105', 'BVTD113'],
    name: 'Sewing Techniques (Practical)',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 9,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 4,
    totalCredits: 4,
    practicalMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 12',
    overview: 'Hands-on practical training in sewing machine parts, maintenance, hand stitches, seam construction (plain, French, run & fell, counter), fullness (yokes, gathers, darts, pleats), plackets, pockets, sleeves, and collars.',
    courseObjectives: [
      'To impart knowledge of sewing techniques',
      'To apply the knowledge for basic stitching'
    ],
    booksPrescribed: [
      'The Art of couture sewing by Zoya Nudelman (Bloomsbury Academic)',
      'The sewing Book by Alison Smith (March 30, 2009)',
      'Sewing Techniques and Patterns by Marie-Naelle Bayard (Sterling)',
      'Complete Guide to Sewing by Reader\'s Digest',
      'The ultimate sewing book by Maggi McCormick'
    ],
    instructionsForPaperSetters: 'Paper will be set on the spot by the examiner.'
  },
  {
    code: 'BVTD106',
    aliases: ['BVTD106', 'BVTD 106', 'BVTD111', 'BVTD112'],
    name: 'Design Foundation & Basics of Textiles',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'THEORY_AND_PRACTICAL',
    hoursPerWeek: 9,
    lectureCredits: 2,
    tutorialCredits: 0,
    practicalCredits: 2,
    totalCredits: 4,
    theoryMarks: 37,
    practicalMarks: 37,
    internalAssessmentMarks: 26,
    totalMarks: 100,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 13-15',
    overview: 'Study of elements and principles of design, art media, fibre classification and properties, yarn spinning classification, fabric construction, colour wheel rendering, and microscopic/burning tests.',
    courseObjectives: ['To help students understand elements and principles of design', 'To classify fibres, yarns and fabric construction types'],
    booksPrescribed: [
      'Textiles Second Edition by Norman Hollen & Jane Saddler (The Macmillan Company)',
      'Textiles Fiber to Fabric by Bernard P. Corbman (McGraw Hill)',
      'Modern Textiles by Dorothy Siegert Lyle (John Wiley & Sons)',
      'Be an Artist in 10 Steps by Ian Sidaway & Patricia Seligman'
    ],
    instructionsForPaperSetters: 'There will be five sections. Section A carries 9 marks (compulsory short questions). Sections B, C, D, E set from Unit I, II, III, IV respectively (2 questions of 7 marks each, attempt 1 per section).'
  },
  {
    code: 'BVTD107',
    aliases: ['BVTD107', 'BVTD 107', 'BVTD114'],
    name: 'Introduction to Entrepreneurship',
    semesterNumber: 1,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MINOR',
    type: 'THEORY',
    hoursPerWeek: 3,
    lectureCredits: 4,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 4,
    theoryMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 16',
    overview: 'Entrepreneurship concept, Schumpeterian functions, need, traits of entrepreneurs, development process, institutional support systems (DIC, MSME), and barriers to entrepreneurship.',
    courseObjectives: ['To impart the knowledge of Entrepreneurship - which will help the students to start their enterprise.'],
    booksPrescribed: [
      'Entrepreneurial Development by Dr. S. Moharana and Dr. Dash (RBSA Publishers, Jaipur)',
      'Entrepreneurial Development by S.S. Khanna (S. Chand & Company Ltd.)',
      'Entrepreneurial Development by C.B. Gupta and N.P. Srinivasan (Sultan Chand & Sons)'
    ],
    instructionsForPaperSetters: 'Section A carries 15 marks (compulsory short questions). Sections B, C, D, E set from Unit I, II, III, IV (2 questions of 15 marks each, attempt 1 per unit).'
  },

  // SEMESTER 2 (Official Khalsa College Syllabus Page 3 & Pages 17-25)
  {
    code: 'BVTD101_S2',
    aliases: ['BVTD 101', 'BVTD101_S2'],
    name: 'Communication Skills in English-II',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'ABILITY_ENHANCEMENT',
    type: 'THEORY_AND_PRACTICAL',
    hoursPerWeek: 3,
    lectureCredits: 2,
    tutorialCredits: 0,
    practicalCredits: 1,
    totalCredits: 3,
    theoryMarks: 25,
    practicalMarks: 12,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 18',
    overview: 'Listening skills, telephone etiquette, note-making, conversational skills, and interview techniques.',
    booksPrescribed: ['Oxford Guide to Effective Writing and Speaking by John Seely']
  },
  {
    code: 'BVTD102_S2',
    aliases: ['BVTD 102', 'BVTD102_S2'],
    name: 'Punjab History & Culture (C 321 to 1000 A.D.)',
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
    theoryMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 20',
    overview: 'Chandragupta Maurya & Ashoka, Kushans, Guptas, Vardhanas, political developments 7th-1000 A.D., development of language, literature, art & architecture.'
  },
  {
    code: 'BVTD103_S2',
    aliases: ['BVTD 103', 'BVTD103_S2'],
    name: 'Drug Abuse: Management and Prevention (Compulsory)',
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
    internalAssessmentMarks: 0,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 21-22',
    overview: 'Prevention of drug abuse, role of family & school, media awareness campaigns, and NDPs statutory legislation.'
  },
  {
    code: 'BVTD104_S2',
    aliases: ['BVTD 104', 'CS-BVTD121'],
    name: 'Computer Application–II (Practical)',
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
    practicalMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 17',
    overview: 'CAD tools for garment pattern drafting, digitizing, croquis rendering, and fabric motif creation.'
  },
  {
    code: 'BVTD105_S2',
    aliases: ['BVTD 105', 'BVTD122'],
    name: 'Garment Sewing (Practical)',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 12,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 4,
    totalCredits: 4,
    practicalMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 23',
    overview: 'Designing, drafting and construction of Kids wear (A line frock, Romper, Night suit) and Women’s wear (Petticoat, Blouse, Kameez, Salwar/churidaar).',
    courseObjectives: ['To enable the students to construct kids and women’s wear.'],
    booksPrescribed: [
      'Sewing Basics by Wendy Gardiner (2003)',
      'The Art of couture sewing by Zoya Nudelman (2009)',
      'The Sewing Book by Alison Smith (2009)'
    ]
  },
  {
    code: 'BVTD106_S2',
    aliases: ['BVTD 106', 'BVTD121'],
    name: 'Introduction to Fashion (Theory)',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'THEORY',
    hoursPerWeek: 3,
    lectureCredits: 4,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 4,
    theoryMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 24',
    overview: 'Study of Fashion terminology (fad, classic, high fashion, mass fashion, haute couture, pret-a-porter, boutique, silhouette, Fashion cycle), Indian & International designers, World fashion centers, and Fashion forecasting.',
    booksPrescribed: [
      'Fashion From Concept to Consumer by Gini Stephens Frings',
      'Ramp: The Business of Indian Fashion by Hindol Sengupta',
      'Introduction to Fashion Design by John Ireland'
    ]
  },
  {
    code: 'BVTD107_S2',
    aliases: ['BVTD 107', 'BVTD124'],
    name: 'Enterprise Planning (Theory)',
    semesterNumber: 2,
    yearNumber: 1,
    status: 'AVAILABLE',
    category: 'MINOR',
    type: 'THEORY',
    hoursPerWeek: 3,
    lectureCredits: 4,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 4,
    theoryMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 25',
    overview: 'Business planning, steps in planning process, resource planning, financial strategy, knowing the market, and marketing techniques for business promotion.',
    booksPrescribed: [
      'Entrepreneurial Development by Dr. S. Moharana and Dr. Dash',
      'Entrepreneurial Development by S.S. Khanna',
      'Entrepreneurial Development by C.B. Gupta and N.P. Srinivasan'
    ]
  }
];

export const INITIAL_TOPICS = [
  // BVTD 104 - Computer Application-I (Syllabus Page 10)
  {
    topicId: 'bvtd104_u1_t1',
    subjectCode: 'BVTD104',
    unitNumber: 1,
    unitTitle: 'Unit -I: Introduction to Computer & Data Processing',
    title: 'Introduction to Computer & Classification',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 10',
    overview: 'Block diagram of computer, Evolution of Computer, Classification of Computers: Based on Generation, based on Size (Micro, Mini, Mainframe, Super, Notebook, Personal Computer, Workstation), Based on Data Processing Techniques (Analog, Digital and Hybrid Computers), Applications of computer, career in computer.',
    keyPoints: [
      'Block diagram of computer: Input Unit, Central Processing Unit (ALU + Control Unit), Memory Unit, and Output Unit.',
      'Computers classified by Generation (1st to 5th), Size (Micro, Mini, Mainframe, Super, Workstation), and Processing Technique (Analog, Digital, Hybrid).'
    ],
    importantTerms: {
      'ALU': 'Arithmetic Logic Unit performing fundamental mathematical and logical operations.',
      'Hybrid Computer': 'System combining analog high-speed measurement with digital precision calculation.'
    }
  },
  {
    topicId: 'bvtd104_u1_t2',
    subjectCode: 'BVTD104',
    unitNumber: 1,
    unitTitle: 'Unit -I: Introduction to Computer & Data Processing',
    title: 'Data Processing & Information Concepts',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 10',
    overview: 'Data Capturing, data Storage, data retrieval, processing methodologies (Batch processing, online processing, Real-time processing), Data Processing Techniques. Concepts of data and information: Structured Data, Unstructured Data, Semi-structured Data, Difference between data and information.',
    keyPoints: [
      'Data processing methodologies include Batch processing, Online processing, and Real-time processing.',
      'Structured Data (tables/databases), Semi-structured Data (JSON/XML), and Unstructured Data (video/images/audio).'
    ],
    importantTerms: {
      'Batch Processing': 'Execution of a series of non-interactive jobs grouped together.',
      'Structured Data': 'Highly organized data formatted into fixed database fields and rows.'
    }
  },
  {
    topicId: 'bvtd104_u2_t1',
    subjectCode: 'BVTD104',
    unitNumber: 2,
    unitTitle: 'Unit-II: Hardware, Software & MS-Word',
    title: 'Computer Hardware & Input/Output Devices',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 10',
    overview: 'Hardware (Input devices: Keyboard, mouse, light pen, touch screen, Bar Code reader, Joystick, MICR, OMR, OCR, handheld terminals, vision input systems). Output Devices: Monitor, Printers (Line, Character, Page), plotters, voice response units. Primary & Secondary Storage Devices.',
    keyPoints: [
      'Source data automation includes MICR (Magnetic Ink Character Recognition), OMR (Optical Mark Recognition), and OCR.',
      'Printers categorized into Character (Dot Matrix), Line (Line Printer), and Page Printers (Laser).'
    ],
    importantTerms: {
      'OCR': 'Optical Character Recognition converting printed text images into editable machine text.',
      'MICR': 'Magnetic Ink Character Recognition used primarily for automated bank cheque processing.'
    }
  },
  {
    topicId: 'bvtd104_u2_t2',
    subjectCode: 'BVTD104',
    unitNumber: 2,
    unitTitle: 'Unit-II: Hardware, Software & MS-Word',
    title: 'Software Systems & MS-Word Formatting',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 10',
    overview: 'Need of software, System Software (Operating System, System Utilities, device drivers), Application Software (General Purpose, customized software). Introduction to MS-Word: General Formatting, Editing, spell-grammar check, printing and saving, Mail merge.',
    keyPoints: [
      'System software manages hardware operations; Application software performs specific user tasks.',
      'Mail Merge in MS-Word combines a main document template with a data source recipient list.'
    ],
    importantTerms: {
      'Mail Merge': 'Word processing feature automating mass personalized document creation.',
      'Device Driver': 'Specialized system program enabling OS communication with hardware peripherals.'
    }
  },

  // BVTD 105 - Sewing Techniques (Syllabus Page 12)
  {
    topicId: 'bvtd105_sec1_t1',
    subjectCode: 'BVTD 105',
    unitNumber: 1,
    unitTitle: 'Section-I: Sewing Machine & Stitching Fundamentals',
    title: 'Parts of Sewing Machine, Care & Maintenance',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 12',
    overview: 'Introduction to different parts of sewing machine and their care and maintenance.',
    keyPoints: [
      'Identification of feed dog, needle bar, take-up lever, bobbin case, and tension discs.',
      'Routine cleaning, oiling, and needle replacement maintenance.'
    ],
    importantTerms: {
      'Feed Dog': 'Serrated teeth beneath presser foot advancing fabric per stitch.'
    }
  },
  {
    topicId: 'bvtd105_sec1_t2',
    subjectCode: 'BVTD 105',
    unitNumber: 1,
    unitTitle: 'Section-I: Sewing Machine & Stitching Fundamentals',
    title: 'Basic Hand Sewing Techniques',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 12',
    overview: 'Basic hand sewing techniques: temporary basting, running stitch, backstitch, hemming.',
    keyPoints: [
      'Temporary vs permanent hand stitches.',
      'Even basting for fabric layer alignment.'
    ],
    importantTerms: {
      'Basting': 'Long temporary stitches securing fabric before machine sewing.'
    }
  },
  {
    topicId: 'bvtd105_sec1_t3',
    subjectCode: 'BVTD 105',
    unitNumber: 1,
    unitTitle: 'Section-I: Sewing Machine & Stitching Fundamentals',
    title: 'Seams and Seam Finishes',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 12',
    overview: 'Seams and seam finishes, plain, run and fell, French, counter.',
    keyPoints: [
      'Plain seam: standard structural join.',
      'French seam: self-enclosed double stitch seam encasing raw edges inside a fold.'
    ],
    importantTerms: {
      'French Seam': 'Enclosed seam hiding raw edges inside fold.'
    }
  },

  // BVTD 106 - Design Foundation & Basics of Textiles (Syllabus Page 13)
  {
    topicId: 'bvtd106_u1_t1',
    subjectCode: 'BVTD106',
    unitNumber: 1,
    unitTitle: 'UNIT -I: Art Media & Elements of Design',
    title: 'Elements of Design & Art Media',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 13',
    overview: 'Different art media and its applications. Elements of design (colour, line, shape and space, light, pattern, texture).',
    keyPoints: [
      'Art media: watercolors, poster colors, pencils, markers.',
      '7 Elements of design: colour, line, shape, space, light, pattern, texture.'
    ],
    importantTerms: {
      'Texture': 'Visual or tactile surface quality of a fabric or design.'
    }
  },
  {
    topicId: 'bvtd106_u2_t1',
    subjectCode: 'BVTD106',
    unitNumber: 2,
    unitTitle: 'UNIT -II: Principles of Design',
    title: 'Principles of Design',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 13',
    overview: 'Principles of design (harmony, balance, proportion, rhythm, emphasis).',
    keyPoints: [
      'Balance: symmetrical vs asymmetrical weight distribution.',
      'Emphasis: focal point attracting viewer attention.'
    ],
    importantTerms: {
      'Harmony': 'Unity of all design elements creating a cohesive aesthetic whole.'
    }
  },
  {
    topicId: 'bvtd106_u3_t1',
    subjectCode: 'BVTD106',
    unitNumber: 3,
    unitTitle: 'UNIT -III: Design, Fibre & Yarn Classification',
    title: 'Fibre & Yarn Classification and Properties',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 13',
    overview: 'Design and its types. Fibre and its classification, properties and characteristics. Yarn and its classification and properties.',
    keyPoints: [
      'Natural vs manufactured fibre classification.',
      'Yarn classification: simple, ply, novelty, and textured yarns.'
    ],
    importantTerms: {
      'Tenacity': 'Tensile strength of fibre expressed in grams per denier.'
    }
  },
  {
    topicId: 'bvtd106_u4_t1',
    subjectCode: 'BVTD106',
    unitNumber: 4,
    unitTitle: 'UNIT -IV: Fabric Construction',
    title: 'Types of Fabric Construction',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 13',
    overview: 'Types of fabric construction (weaving, knitting, felting, bonding, non-woven).',
    keyPoints: [
      'Woven fabrics formed by interlacing warp and weft yarns at right angles.',
      'Knitted fabrics formed by interlooping yarn loops.'
    ],
    importantTerms: {
      'Interlacement': 'Crossing of warp and weft yarns over and under each other.'
    }
  },

  // BVTD 107 - Introduction to Entrepreneurship (Syllabus Page 16)
  {
    topicId: 'bvtd107_u1_t1',
    subjectCode: 'BVTD107',
    unitNumber: 1,
    unitTitle: 'UNIT-I: Entrepreneurship Fundamentals',
    title: 'Entrepreneurship: Concept, Functions and Need',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 16',
    overview: 'Entrepreneurship concept, functions and need.',
    keyPoints: [
      'Entrepreneurship as risk-bearing innovation and venture organization.',
      'Functions: opportunity scouting, resource assembly, enterprise creation.'
    ],
    importantTerms: {
      'Entrepreneur': 'Person organizing and operating a business venture.'
    }
  },
  {
    topicId: 'bvtd107_u2_t1',
    subjectCode: 'BVTD107',
    unitNumber: 2,
    unitTitle: 'UNIT-II: Characteristics & Process',
    title: 'Characteristics & Process of Entrepreneurship Development',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 16',
    overview: 'Characteristics of entrepreneurship. Process of entrepreneurship development.',
    keyPoints: [
      'Traits: vision, risk tolerance, self-confidence, perseverance.',
      'Development process: idea -> appraisal -> execution -> management.'
    ],
    importantTerms: {
      'Feasibility Study': 'Appraisal of commercial and technical viability.'
    }
  },
  {
    topicId: 'bvtd107_u3_t1',
    subjectCode: 'BVTD107',
    unitNumber: 3,
    unitTitle: 'UNIT-III: Institutional Support',
    title: 'Help and Support to Entrepreneurs',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 16',
    overview: 'Help and support to entrepreneurs (DIC, MSME policies, subsidies, banking loans).',
    keyPoints: [
      'District Industries Centre (DIC) single-window clearance.',
      'MSME credit schemes and technology upgrade subsidies (TUFS).'
    ],
    importantTerms: {
      'DIC': 'District Industries Centre guiding local small businesses.'
    }
  },
  {
    topicId: 'bvtd107_u4_t1',
    subjectCode: 'BVTD107',
    unitNumber: 4,
    unitTitle: 'UNIT-IV: Entrepreneurial Challenges',
    title: 'Barriers to Entrepreneurship',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 16',
    overview: 'Barriers to entrepreneurship (financial, administrative, market, social barriers).',
    keyPoints: [
      'Capital shortages and lack of collateral.',
      'Regulatory compliance red tape.'
    ],
    importantTerms: {
      'Red Tape': 'Excessive administrative procedural delay.'
    }
  },

  // BVTD 106 (Sem II) - Introduction to Fashion (Syllabus Page 24)
  {
    topicId: 'bvtd106_s2_u1_t1',
    subjectCode: 'BVTD106_S2',
    unitNumber: 1,
    unitTitle: 'UNIT-I: Fashion Terminology & Fashion Cycle',
    title: 'Fashion Terminology & Fashion Cycle',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 24',
    overview: 'Fashion terminology – fad, classic, high fashion, mass fashion, haute couture, pret-a-porter, boutique and silhouette. Fashion cycle.',
    keyPoints: [
      'Differences between fad, classic, haute couture, and pret-a-porter.',
      '5 Stages of fashion cycle: Introduction, Rise, Culmination, Decline, Obsolescence.'
    ],
    importantTerms: {
      'Haute Couture': 'High-end custom fitted couture garments.',
      'Pret-a-porter': 'Ready-to-wear factory manufactured designer clothing.'
    }
  }
];

export const INITIAL_PRACTICALS = [
  // BVTD 104 Practical (Page 11)
  {
    practicalId: 'bvtd104_p1',
    subjectCode: 'BVTD104',
    title: 'MS Word General Formatting & Mail Merge',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 11',
    objective: 'Introduction to MS Word General Formatting, Editing, Spell Grammar Check, Printing and saving, Mail Merge.'
  },

  // BVTD 105 Practical (Page 12)
  {
    practicalId: 'bvtd105_p1_seams',
    subjectCode: 'BVTD105',
    title: 'Seams and Seam Finishes Construction',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 12',
    objective: 'Seams and seam finishes, plain, run and fell, French, counter.'
  },
  {
    practicalId: 'bvtd105_p2_fullness',
    subjectCode: 'BVTD105',
    title: 'Fullness Control: Yokes, Gathers, Darts & Pleats',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 12',
    objective: 'Fullness – yoke with fullness, gathers, darts, pleats.'
  },
  {
    practicalId: 'bvtd105_p3_plackets_pockets',
    subjectCode: 'BVTD105',
    title: 'Plackets & Pockets Construction',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 12',
    objective: 'Plackets- French placket, continuous, extended placket. Pockets- patch, welt, in seam, kurta.'
  },
  {
    practicalId: 'bvtd105_p4_sleeves_collars',
    subjectCode: 'BVTD105',
    title: 'Sleeves & Collars Construction',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 12',
    objective: 'Sleeves- plain, puff, bishop, bell, cap, raglan, kimono and dolman. Collars – flat Peter Pan collar, shawl, mandarin, cape collar.'
  },

  // BVTD 106 Practical (Page 15)
  {
    practicalId: 'bvtd106_p1_rendering',
    subjectCode: 'BVTD106',
    title: 'Colour Wheel & Croquis Rendering Practical',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 15',
    objective: 'Nature study, Colour wheel, Colour schemes, Fleshing and motion, Analysis of hands, feet and face, Rendering of croquis with colour, Collection of different yarns, Microscopic and burning test.'
  },

  // BVTD 105 (Sem II) Practical (Page 23)
  {
    practicalId: 'bvtd105_s2_p1',
    subjectCode: 'BVTD105_S2',
    title: 'Kids & Women’s Garment Construction',
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 23',
    objective: 'Designing, drafting and construction of Kids wear (A line frock, Romper, Night suit) and Women’s wear (Petticoat, Blouse, Kameez, Salwar/churidaar).'
  }
];

export const INITIAL_QUIZZES = [
  {
    questionId: 'bvtd105_q1',
    subjectCode: 'BVTD105',
    unitNumber: 1,
    topicId: 'bvtd105_sec1_t3',
    question: 'Which seam completely encases raw fabric edges inside a folded second row of stitching?',
    options: ['Plain Seam', 'French Seam', 'Lapped Seam', 'Bound Seam'],
    correctIndex: 1,
    explanation: 'French seam is a self-enclosed seam where raw edge allowances are encased inside a folded row of stitching.',
    difficulty: 'EASY'
  },
  {
    questionId: 'bvtd107_q1',
    subjectCode: 'BVTD107',
    unitNumber: 1,
    topicId: 'bvtd107_u1_t1',
    question: 'According to Joseph Schumpeter, what is the primary defining function of an entrepreneur?',
    options: ['Capital investment only', 'Innovation', 'Routine administration', 'Manual labor'],
    correctIndex: 1,
    explanation: 'Schumpeter defined the entrepreneur as an innovator who introduces new products, processes, or markets.',
    difficulty: 'EASY'
  },
  {
    questionId: 'bvtd104_q1',
    subjectCode: 'BVTD104',
    unitNumber: 1,
    topicId: 'bvtd104_u1_t1',
    question: 'In a computer system, what does ALU stand for?',
    options: ['Arithmetic Logic Unit', 'Automated Linear Unit', 'Analog Logic Utility', 'Array Level Unit'],
    correctIndex: 0,
    explanation: 'ALU stands for Arithmetic Logic Unit, performing mathematical and logical operations in the CPU.',
    difficulty: 'EASY'
  }
];

export const INITIAL_FLASHCARDS = [
  {
    cardId: 'fc_bvtd105_1',
    subjectCode: 'BVTD105',
    unitNumber: 1,
    topicId: 'bvtd105_sec1_t1',
    type: 'IDENTIFICATION',
    front: 'What is the function of the Feed Dog in a sewing machine?',
    back: 'Serrated metal teeth beneath the needle plate that advance the fabric forward by one stitch length during each sewing cycle.',
    categoryHint: 'Machine Parts'
  },
  {
    cardId: 'fc_bvtd107_1',
    subjectCode: 'BVTD107',
    unitNumber: 1,
    topicId: 'bvtd107_u1_t1',
    type: 'DEFINITION',
    front: 'What is a Detailed Project Report (DPR)?',
    back: 'A comprehensive document detailing technical, financial, managerial, and commercial feasibility of a proposed business venture, used by banks to evaluate loan approvals.',
    categoryHint: 'Business Planning'
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
    resourceId: 'res_bvtd105_sewing',
    title: 'The Art of Couture Sewing (Zoya Nudelman)',
    subjectCode: 'BVTD105',
    category: 'Textbook',
    description: 'Prescribed reference book for advanced stitching, seams, and garment construction details (Bloomsbury Academic).',
    downloadSize: '18.5 MB',
    format: 'PDF Syllabus Reference'
  },
  {
    resourceId: 'res_bvtd107_ent',
    title: 'Entrepreneurial Development Guide (Dr. S. Moharana)',
    subjectCode: 'BVTD107',
    category: 'Textbook',
    description: 'Prescribed reference book for MSME enterprise planning, institutional support, and DPR formulation.',
    downloadSize: '12.3 MB',
    format: 'PDF Study Manual'
  },
  {
    resourceId: 'res_bvtd106_textile',
    title: 'Textiles Second Edition (Norman Hollen & Jane Saddler)',
    subjectCode: 'BVTD106',
    category: 'Textbook',
    description: 'Prescribed reference book for fibre chemistry, physical structure, and polymer properties (The Macmillan Company).',
    downloadSize: '14.2 MB',
    format: 'PDF Study Manual'
  }
];
