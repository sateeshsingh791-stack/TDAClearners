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
        { number: 1, title: 'Semester 1', status: 'AVAILABLE', totalCredits: 25, totalMarks: 400, totalHoursPerWeek: 30 },
        { number: 2, title: 'Semester 2', status: 'AVAILABLE', totalCredits: 29, totalMarks: 400, totalHoursPerWeek: 40 }
      ]
    },
    {
      yearNumber: 2,
      title: '2nd Year (Advanced Diploma in Textile Design & Apparel Technology)',
      status: 'AVAILABLE',
      semesters: [
        { number: 3, title: 'Semester 3', status: 'AVAILABLE', totalCredits: 26, totalMarks: 400, totalHoursPerWeek: 30 },
        { number: 4, title: 'Semester 4', status: 'AVAILABLE', totalCredits: 28, totalMarks: 400, totalHoursPerWeek: 35 }
      ]
    },
    {
      yearNumber: 3,
      title: '3rd Year (B.Voc. Degree in Textile Design & Apparel Technology)',
      status: 'COMING_SOON',
      semesters: [
        { number: 5, title: 'Semester 5', status: 'COMING_SOON', totalCredits: 0, totalMarks: 0, totalHoursPerWeek: 0 },
        { number: 6, title: 'Semester 6', status: 'COMING_SOON', totalCredits: 0, totalMarks: 0, totalHoursPerWeek: 0 }
      ]
    }
  ]
};

export const INITIAL_SUBJECTS = [
  // ==================== SEMESTER 1 ====================
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
    courseObjectives: ['To impart knowledge of sewing techniques', 'To apply the knowledge for basic stitching'],
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

  // ==================== SEMESTER 2 ====================
  {
    code: 'BVTD101-S2',
    aliases: ['BVTD101-S2', 'BVTD 101 S2'],
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
    code: 'BVTD102-S2',
    aliases: ['BVTD102-S2', 'BVTD 102 S2'],
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
    code: 'BVTD103-S2',
    aliases: ['BVTD103-S2', 'BVTD 103 S2'],
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
    code: 'BVTD104-S2',
    aliases: ['BVTD104-S2', 'BVTD 104 S2', 'CS-BVTD121'],
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
    code: 'BVTD105-S2',
    aliases: ['BVTD105-S2', 'BVTD 105 S2', 'BVTD122'],
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
    code: 'BVTD106-S2',
    aliases: ['BVTD106-S2', 'BVTD 106 S2', 'BVTD121'],
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
    code: 'BVTD107-S2',
    aliases: ['BVTD107-S2', 'BVTD 107 S2', 'BVTD124'],
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
  },

  // ==================== SEMESTER 3 ====================
  {
    code: 'BVTD102-S3',
    aliases: ['BVTD102-S3', 'BVTD 102 S3'],
    name: 'Basics of Sewing Printing & Dyeing',
    semesterNumber: 3,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'THEORY',
    hoursPerWeek: 3,
    lectureCredits: 3,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 3,
    theoryMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 27',
    overview: 'Study of sewing terminology, anthropometric body measurements, standardized size charts, printing techniques (block, screen, resist, stencil), and dye classifications.',
    courseObjectives: ['To impart the knowledge of basics of sewing and printing', 'To apply the knowledge in garment production and textile units'],
    booksPrescribed: [
      'Masterpieces of Indian Textiles by J. Mehta (1970)',
      'Manual of Textile Printing by Joyce Storey (1974)',
      'Pattern Making for Fashion Design by Helen Joseph Armstrong (2000)'
    ],
    instructionsForPaperSetters: 'There will be five sections. Section A Carries 9 marks and is compulsory (8 short answer questions of 1.5 marks each). Sections B, C, D and E set from unit I, II, III, & IV respectively (2 questions of 7 marks each, attempt 1 per section).'
  },
  {
    code: 'BVTD103-S3',
    aliases: ['BVTD103-S3', 'BVTD 103 S3'],
    name: 'Pattern Making and Grading (Practical)',
    semesterNumber: 3,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 3,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 4,
    totalCredits: 4,
    practicalMarks: 56,
    internalAssessmentMarks: 19,
    totalMarks: 75,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 28-29',
    overview: 'Child and adult bodice/sleeve block drafting, drafting sleeves, collars, skirts, contoured patterns, dart manipulation (pivot and slash-and-spread methods), and pattern grading.',
    courseObjectives: ['To teach different aspects of Pattern Making and Grading', 'To enable students to become pattern makers in Garment Industry'],
    booksPrescribed: [
      'Creative Pattern Skills for Fashion Design by Bernard Zamkoff & Jeanne Price (1990)',
      'Pattern Making for Fashion Design by Helen Joseph Armstrong (2000)',
      'Fast Fit - Easy Pattern Alterations by Sandra Betzina (2003)'
    ],
    instructionsForPaperSetters: 'Practical Paper will be set on the spot by the examiner.'
  },
  {
    code: 'BVTD104-S3',
    aliases: ['BVTD104-S3', 'BVTD 104 S3'],
    name: 'History of Costumes',
    semesterNumber: 3,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MINOR',
    type: 'THEORY',
    hoursPerWeek: 6,
    lectureCredits: 4,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 4,
    theoryMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 30-31',
    overview: 'Study of ancient Indian costumes (Mauryan, Shunga, Satvahana, Gupta), traditional regional costumes of India (North, West, East, South), and global costumes (Greek, Roman, Egyptian, Byzantine).',
    courseObjectives: ['To impart the knowledge of world and Indian historic costumes.'],
    booksPrescribed: [
      'Indian Costumes by A. Biswas (2003)',
      'Traditional Indian Costumes and Textiles by Parul Bhatnagar (2004)',
      'Costumes and Textiles of Royal India by Ritu Kumar (1999)'
    ],
    instructionsForPaperSetters: 'Section A carries 9 marks compulsory (8 short questions of 1.5 marks). Sections B, C, D, E set from unit I, II, III, & IV respectively (2 questions of 7 marks each).'
  },
  {
    code: 'BVTD105-S3',
    aliases: ['BVTD105-S3', 'BVTD 105 S3'],
    name: 'CAD-I (Practical)',
    semesterNumber: 3,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MINOR',
    type: 'PRACTICAL',
    hoursPerWeek: 3,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 3,
    totalCredits: 3,
    practicalMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 32',
    overview: 'Computerized sloper development for child/adult blocks, grain line manipulation, dart/seam manipulation, CAD pattern layout for skirts, sleeves, and collars.',
    courseObjectives: ['To understand fashion design concepts on computer', 'To acquaint students with CAD applications in Fashion Designing'],
    booksPrescribed: [
      'Software Manuals',
      'Pattern Making for Fashion Designing by Helen Joseph',
      'Pattern Grading for Women’s Clothes by Gerry Coklin'
    ]
  },
  {
    code: 'BVTD106-S3',
    aliases: ['BVTD106-S3', 'BVTD 106 S3'],
    name: 'Design Development-I (Practical)',
    semesterNumber: 3,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 3,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 4,
    totalCredits: 4,
    practicalMarks: 56,
    internalAssessmentMarks: 19,
    totalMarks: 75,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 33-34',
    overview: 'Flat sketching of fashion details (pockets, sleeves, collars, necklines, skirts), fabric rendering (net, cotton, silk, velvet, denim, fur), silhouette draping on figures, and accessory sketching.',
    courseObjectives: ['To help students to understand flat sketching for fashion illustration.'],
    booksPrescribed: [
      'Inside Fashion Design by Sharon Lee Tata (1977)',
      'Fashion Design Drawing and Presentation by Patrick John Ireland (1996)',
      'Fashion Illustration by Bina Abling (2008)'
    ]
  },
  {
    code: 'BVTD107-S3',
    aliases: ['BVTD107-S3', 'BVTD 107 S3'],
    name: 'Garment Construction (Practical)',
    semesterNumber: 3,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 9,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 4,
    totalCredits: 4,
    practicalMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 35',
    overview: 'Design and construction of theme-based women’s dresses: Indian traditional dress, Western party wear, Office wear, Night wear, and Casual wear (culottes, pleated skirts, tops with cowl/turtle neckline).',
    courseObjectives: ['To enable the students to construct theme based garments.'],
    booksPrescribed: [
      'Pattern Making for Fashion Designing by Helen Joseph',
      'The Art of couture sewing by Zoya Nudelman',
      'Technology of Clothing Manufacture by Harold Carr'
    ]
  },

  // ==================== SEMESTER 4 ====================
  {
    code: 'BVTD102-S4',
    aliases: ['BVTD102-S4', 'BVTD 102 S4'],
    name: 'Traditional Textiles',
    semesterNumber: 4,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'THEORY_AND_PRACTICAL',
    hoursPerWeek: 6,
    lectureCredits: 2,
    tutorialCredits: 0,
    practicalCredits: 2,
    totalCredits: 4,
    theoryMarks: 37,
    practicalMarks: 37,
    internalAssessmentMarks: 26,
    totalMarks: 100,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 36-38',
    overview: 'Study of traditional Indian colored textiles (Patola, Ikat, Bandhani), woven textiles (Chanderi, Maheshwari, Kanjeevaram, Baluchari, Brocades), painted textiles (Kalamkari), traditional embroideries (Phulkari, Kantha, Chikankari, Kashida, Chambarumal), and sample making.',
    courseObjectives: ['To gain knowledge of traditional textiles of India.'],
    booksPrescribed: [
      'Indian Embroidery by Savitri Pandit',
      'Traditional Indian Textiles by Parul Bhatnagar',
      'Masterpieces of Indian Textiles by J. Mehta (1970)'
    ]
  },
  {
    code: 'BVTD103-S4',
    aliases: ['BVTD103-S4', 'BVTD 103 S4'],
    name: 'Draping (Practical)',
    semesterNumber: 4,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 3,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 3,
    totalCredits: 3,
    practicalMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 39',
    overview: 'Draping of basic bodice block (front, back), princess line variations, cowl necklines, French darts, intersecting darts, asymmetric darts, and bustier draping.',
    courseObjectives: ['To impart the knowledge of Indian and Global fashion market draping techniques.'],
    booksPrescribed: [
      'Creative Pattern Skills for Fashion Design by Bernard Zamkoff (1990)',
      'Draping for Apparel Design by Helen Joseph-Armstrong (1999)',
      'The Art of Fashion Draping by Connie Amaden-Crawford (1995)'
    ]
  },
  {
    code: 'BVTD104-S4',
    aliases: ['BVTD104-S4', 'BVTD 104 S4'],
    name: 'Quality Control',
    semesterNumber: 4,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MINOR',
    type: 'THEORY',
    hoursPerWeek: 3,
    lectureCredits: 3,
    tutorialCredits: 0,
    practicalCredits: 0,
    totalCredits: 3,
    theoryMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 40',
    overview: 'Quality control definitions and importance, pre-sewing and sewing fabric defects, Total Quality Management (TQM), accessory testing, apparel quality assurance in packing, and shipping procedures.',
    courseObjectives: ['To enable the students to learn about quality control in apparel industries.'],
    booksPrescribed: [
      'Managing Quality in the Apparel Industry by Pradip Mehta (NIFT)',
      'Technology of Clothing Manufacture by Harold Carr',
      'Total Quality Management by Wiley Eastern'
    ]
  },
  {
    code: 'BVTD105-S4',
    aliases: ['BVTD105-S4', 'BVTD 105 S4'],
    name: 'CAD-II (Practical)',
    semesterNumber: 4,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MINOR',
    type: 'PRACTICAL',
    hoursPerWeek: 3,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 3,
    totalCredits: 3,
    practicalMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 41',
    overview: 'Introduction to basic tools of fashion software and designing 10 outfits on themes: Season, Party wear, Sports, Casual, Beach, Club, Night, Evening, Uniform.',
    courseObjectives: ['To understand fashion design concepts on computer and CAD software applications.'],
    booksPrescribed: ['Software Manuals']
  },
  {
    code: 'BVTD106-S4',
    aliases: ['BVTD106-S4', 'BVTD 106 S4'],
    name: 'Design Development-II (Practical)',
    semesterNumber: 4,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 3,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 3,
    totalCredits: 3,
    practicalMarks: 37,
    internalAssessmentMarks: 13,
    totalMarks: 50,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 42',
    overview: 'Professional illustrations, croquis rendering, designing 4 dresses per theme (Indian Traditional, Casual, Kids, Western Formal, Office Wear) with Mood Board, Colour Board, Swatch Board, Specification Sheet, and Costing Sheet.',
    courseObjectives: ['To teach students Professional illustrations which will help them in garment designing.'],
    booksPrescribed: [
      'Inside Fashion Design by Sharon Lee Tata (1977)',
      'Fashion Design Drawing and Presentation by Patrick John Ireland (1996)',
      'Figure Drawing for Fashion by Elisabetta Drudi (2002)'
    ]
  },
  {
    code: 'BVTD107-S4',
    aliases: ['BVTD107-S4', 'BVTD 107 S4'],
    name: 'Professional Garment Construction (Practical)',
    semesterNumber: 4,
    yearNumber: 2,
    status: 'AVAILABLE',
    category: 'MAJOR',
    type: 'PRACTICAL',
    hoursPerWeek: 6,
    lectureCredits: 0,
    tutorialCredits: 0,
    practicalCredits: 4,
    totalCredits: 4,
    practicalMarks: 75,
    internalAssessmentMarks: 25,
    totalMarks: 100,
    syllabusSource: 'Official Khalsa College Syllabus',
    syllabusPageRef: 'Page 43',
    overview: 'Design and construction of men’s dresses on any three of the themes: Casual wear, Street wear, Traditional Indian dress, Formal dress.',
    courseObjectives: ['To enable the students to construct Men’s wear.'],
    booksPrescribed: [
      'McCall’s Sewing in Colour (1971)',
      'Singer Sewing Book by Mary Brooks Picken (1943)',
      'Dress Pattern Designing by N. Bray (2003)'
    ]
  }
];

export const INITIAL_TOPICS = [
  // ==================== SEMESTER 1 TOPICS ====================
  {
    topicId: 'bvtd104_u1_t1',
    subjectCode: 'BVTD104',
    unitNumber: 1,
    unitTitle: 'Unit -I: Introduction to Computer & Data Processing',
    title: 'Introduction to Computer & Classification',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Block diagram of computer, evolution of computer, and classification of computers based on generation, size, and processing techniques.',
    keyPoints: [
      'Block diagram of computer: Input Unit, CPU (ALU + Control Unit), Memory Unit, and Output Unit.',
      'Classification of computers based on size (Micro, Mini, Mainframe, Super, Notebook) and processing technique (Analog, Digital, Hybrid).'
    ]
  },
  {
    topicId: 'bvtd104_u1_t2',
    subjectCode: 'BVTD104',
    unitNumber: 1,
    unitTitle: 'Unit -I: Introduction to Computer & Data Processing',
    title: 'Data Processing & Information Concepts',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Data capturing, storage, retrieval, processing methodologies (batch, online, real-time), and types of data (structured, unstructured, semi-structured).',
    keyPoints: [
      'Batch processing executes grouped non-interactive jobs; online and real-time processing handle live transactions.',
      'Data vs Information: Data consists of raw unorganized facts; information is processed, structured data.'
    ]
  },
  {
    topicId: 'bvtd104_u2_t1',
    subjectCode: 'BVTD104',
    unitNumber: 2,
    unitTitle: 'Unit-II: Hardware, Software & MS-Word',
    title: 'Computer Hardware & Input/Output Devices',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Input devices (keyboard, mouse, touch screen, barcode reader, joystick, MICR, OMR, OCR), output devices (monitors, printers, plotters), and storage media.',
    keyPoints: [
      'Source data automation includes MICR (Magnetic Ink Character Recognition), OMR, and OCR.',
      'Primary storage (RAM/ROM) vs Secondary storage (optical and magnetic storage devices).'
    ]
  },

  // ==================== SEMESTER 3 TOPICS ====================
  {
    topicId: 'bvtd102_s3_u1_t1',
    subjectCode: 'BVTD102-S3',
    unitNumber: 1,
    unitTitle: 'UNIT-I: Sewing & Garment Terminology',
    title: 'Garment Sewing Terminology & Markings',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Understanding fundamental sewing terminology including notches, grain, grain lines, centre front, back line, bias, bust line, waistline, seam line, seams, seam allowances, and darts.',
    keyPoints: [
      'Notches and grain lines direct proper fabric alignment along longitudinal or bias threads.',
      'Darts and dart points direct fabric volume towards anatomical body apex contours.'
    ]
  },
  {
    topicId: 'bvtd102_s3_u2_t1',
    subjectCode: 'BVTD102-S3',
    unitNumber: 2,
    unitTitle: 'UNIT-II: Anthropometric Measurements & Size Charts',
    title: 'Anthropometric Body Measurements & Size Charts',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Study of all body measurements, standards of body measurements, importance of standardization, size charts, and drafting principles.',
    keyPoints: [
      'Anthropometric measurement standardization ensures consistent garment sizing across apparel manufacturing.',
      'Drafting translates 3D body dimensions onto 2D flat paper patterns.'
    ]
  },
  {
    topicId: 'bvtd102_s3_u3_t1',
    subjectCode: 'BVTD102-S3',
    unitNumber: 3,
    unitTitle: 'UNIT-III: Printing Techniques',
    title: 'Textile Printing Techniques',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Study of traditional and commercial textile printing techniques: block printing, screen printing, resist printing, roller printing, and stencil printing.',
    keyPoints: [
      'Block and stencil printing offer artisan relief pattern transfer.',
      'Screen printing and roller printing enable continuous high-speed commercial fabric production.'
    ]
  },
  {
    topicId: 'bvtd102_s3_u4_t1',
    subjectCode: 'BVTD102-S3',
    unitNumber: 4,
    unitTitle: 'UNIT-IV: Dyeing Classification',
    title: 'Classification of Dyes & Application Methods',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Classification of textile dyes: acid dyes, basic dyes, direct dyes, and reactive dyes for natural and synthetic fibres.',
    keyPoints: [
      'Acid dyes bind protein fibres (wool, silk); direct and reactive dyes bind cellulosic fibres (cotton).',
      'Reactive dyes form covalent chemical bonds with cellulose for superior wash fastness.'
    ]
  },

  // BVTD104-S3 History of Costumes
  {
    topicId: 'bvtd104_s3_u1_t1',
    subjectCode: 'BVTD104-S3',
    unitNumber: 1,
    unitTitle: 'UNIT-I: Ancient Indian Civilization Costumes',
    title: 'Mauryan, Shunga, Satvahana & Gupta Costumes',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Study of unstitched draped garments, antariya, uttariya, kayabandh, and elaborate headgear across Mauryan, Shunga, Satvahana, and Gupta periods.',
    keyPoints: [
      'Ancient Indian dress relied primarily on draped fabrics (antariya for lower body, uttariya for upper body).',
      'Gupta period introduced stitched garments influenced by Central Asian trade routes.'
    ]
  },
  {
    topicId: 'bvtd104_s3_u2_t1',
    subjectCode: 'BVTD104-S3',
    unitNumber: 2,
    unitTitle: 'UNIT-II: Traditional Costumes of North & West India',
    title: 'Regional Attire of North & West India',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Traditional costumes of North India (Punjab, Jammu & Kashmir, Haryana) and West India (Maharashtra, Gujarat).',
    keyPoints: [
      'Punjabi Salwar Suit and Kashmiri Pheran reflect climatic and cultural heritage.',
      'Gujarati Chaniya Choli and Maharashtrian Nauvari Saree display distinct regional draping.'
    ]
  },
  {
    topicId: 'bvtd104_s3_u3_t1',
    subjectCode: 'BVTD104-S3',
    unitNumber: 3,
    unitTitle: 'UNIT-III: Traditional Costumes of East & South India',
    title: 'Regional Attire of East & South India',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Traditional costumes of East India (Assam, West Bengal, Orissa) and South India (Kerala, Karnataka, Tamil Nadu).',
    keyPoints: [
      'Assamese Mekhela Chador and Bengali Tant sarees.',
      'South Indian Mundum Neriyathum and Kanjeevaram silk sarees.'
    ]
  },
  {
    topicId: 'bvtd104_s3_u4_t1',
    subjectCode: 'BVTD104-S3',
    unitNumber: 4,
    unitTitle: 'UNIT-IV: Global Costumes',
    title: 'Ancient Greek, Roman, Egyptian & Byzantine Costumes',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Study of historic global costumes: Egyptian Kalasiris, Greek Chiton/Himation, Roman Toga, and ornate Byzantine tunics.',
    keyPoints: [
      'Greek Chiton and Roman Toga established classical draped silhouettes.',
      'Byzantine costumes incorporated heavy silk brocades and gem-encrusted dalmatics.'
    ]
  },

  // ==================== SEMESTER 4 TOPICS ====================
  {
    topicId: 'bvtd102_s4_u1_t1',
    subjectCode: 'BVTD102-S4',
    unitNumber: 1,
    unitTitle: 'UNIT-I: Traditional Colored Textiles',
    title: 'Patola, Ikat & Bandhani Craftsmanship',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Study of traditional Indian resist-dyed textiles: Patola (double ikat of Gujarat), Ikat (single ikat of Odisha/Pochampally), and Bandhani (tie-dye of Gujarat & Rajasthan).',
    keyPoints: [
      'Patola involves double resist-dyeing of both warp and weft yarns before weaving.',
      'Bandhani utilizes fine thread tying to create resist dot patterns on fabric surfaces.'
    ]
  },
  {
    topicId: 'bvtd102_s4_u2_t1',
    subjectCode: 'BVTD102-S4',
    unitNumber: 2,
    unitTitle: 'UNIT-II: Traditional Woven Textiles',
    title: 'Chanderi, Maheshwari, Kanjeevaram & Brocades',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Study of royal woven textiles: Chanderi, Maheshwari, Kanjeevaram silk, Baluchari pictorial sarees, and Banaras Brocades.',
    keyPoints: [
      'Chanderi and Maheshwari feature fine sheer silk-cotton weaves with gold zari borders.',
      'Banaras Brocades incorporate intricate zari extra-warp and extra-weft patterning.'
    ]
  },
  {
    topicId: 'bvtd102_s4_u3_t1',
    subjectCode: 'BVTD102-S4',
    unitNumber: 3,
    unitTitle: 'UNIT-III: Traditional Painted Textiles',
    title: 'Kalamkari Hand-Painted Textiles',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Study of hand-painted and block-printed Kalamkari textiles of Andhra Pradesh, utilizing bamboo pens and natural vegetable dyes.',
    keyPoints: [
      'Kalamkari uses tamarind pen (kalam) and natural mordants for narrative figurative art.'
    ]
  },
  {
    topicId: 'bvtd102_s4_u4_t1',
    subjectCode: 'BVTD102-S4',
    unitNumber: 4,
    unitTitle: 'UNIT-IV: Traditional Embroideries of India',
    title: 'Phulkari, Kantha, Chikankari, Kashida & Chambarumal',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'History, decorative stitches, techniques, and color combinations of Phulkari (Punjab), Kantha (Bengal), Chikankari (Lucknow), Kashida (Kashmir), and Chamba Rumal (Himachal Pradesh).',
    keyPoints: [
      'Phulkari utilizes untwisted silk thread (pat) in darning stitch on khaddar.',
      'Chikankari incorporates white shadow work, tepchi, and jaali stitch work.'
    ]
  },
  {
    topicId: 'bvtd104_s4_u1_t1',
    subjectCode: 'BVTD104-S4',
    unitNumber: 1,
    unitTitle: 'UNIT-I: Quality Control Fundamentals',
    title: 'Definition & Importance of Quality Control',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Definition of quality control, importance of quality assurance in garment manufacturing, and customer satisfaction metrics.',
    keyPoints: [
      'Quality control prevents defects during inline assembly rather than relying solely on end-line rejection.'
    ]
  },
  {
    topicId: 'bvtd104_s4_u2_t1',
    subjectCode: 'BVTD104-S4',
    unitNumber: 2,
    unitTitle: 'UNIT-II: Fabric & Sewing Defects',
    title: 'Pre-Sewing & Sewing Defect Analysis',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Analysis of fabric mill defects, shading, bowing, skewed weft, pre-sewing cutting defects, and machine sewing defects (puckering, skipped stitches).',
    keyPoints: [
      'Sewing puckering caused by improper thread tension, feed dog misalignment, or needle heat.'
    ]
  },
  {
    topicId: 'bvtd104_s4_u3_t1',
    subjectCode: 'BVTD104-S4',
    unitNumber: 3,
    unitTitle: 'UNIT-III: Total Quality Management & Accessory Testing',
    title: 'TQM & Garment Accessory Testing',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Concept of Total Quality Management (TQM). Testing procedures for sewing threads, buttons, zippers, laces, hooks, and elastics.',
    keyPoints: [
      'Accessory testing verifies button pull strength, zipper fatigue resistance, and elastic recovery.'
    ]
  },
  {
    topicId: 'bvtd104_s4_u4_t1',
    subjectCode: 'BVTD104-S4',
    unitNumber: 4,
    unitTitle: 'UNIT-IV: Packing Quality Assurance & Shipping',
    title: 'Apparel Packing Quality Assurance & Shipping Procedures',
    isOfficialSyllabusTopic: true,
    sourceLabel: 'Official University Syllabus',
    overview: 'Apparel packing quality assurance, packing types (flat pack, hanger pack), carton specifications, barcode scanning, and shipping procedures.',
    keyPoints: [
      'Pre-shipment audit checks AQL sampling standards before container loading.'
    ]
  }
];

export const INITIAL_PRACTICALS = [
  // ==================== SEMESTER 1 PRACTICALS ====================
  {
    practicalId: 'bvtd104_p1',
    subjectCode: 'BVTD104',
    title: 'MS Word Formatting & Mail Merge Practical',
    objective: 'Introduction to MS Word General Formatting, Editing, Spell Grammar Check, Printing and saving, Mail Merge.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd105_p1_seams',
    subjectCode: 'BVTD105',
    title: 'Seams & Seam Finishes Construction',
    objective: 'Construction of plain seam, run and fell seam, French seam, and counter seam swatches.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd105_p2_fullness',
    subjectCode: 'BVTD105',
    title: 'Fullness Controls Construction',
    objective: 'Construction of yoke with fullness, gathers, single-pointed darts, knife pleats, and box pleats.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd105_p3_plackets_pockets',
    subjectCode: 'BVTD105',
    title: 'Plackets & Pockets Construction',
    objective: 'Construction of French placket, continuous placket, extended placket, patch pocket, welt pocket, in-seam pocket, and kurta pocket.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd105_p4_sleeves_collars',
    subjectCode: 'BVTD105',
    title: 'Sleeves & Collars Construction',
    objective: 'Drafting and sewing of plain, puff, bishop, bell, cap, raglan, kimono, dolman sleeves and Peter Pan, shawl, mandarin, cape collars.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd106_p1_rendering',
    subjectCode: 'BVTD106',
    title: 'Colour Wheel & Croquis Rendering Practical',
    objective: 'Nature study, colour wheel rendering, colour schemes, croquis rendering, yarn collection, and microscopic/burning fibre tests.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },

  // ==================== SEMESTER 2 PRACTICALS ====================
  {
    practicalId: 'bvtd104_s2_p1',
    subjectCode: 'BVTD104-S2',
    title: 'CAD Pattern Drafting & Motif Creation',
    objective: 'CAD tools for garment pattern drafting, digitizing, croquis rendering, and fabric motif creation.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd105_s2_p1',
    subjectCode: 'BVTD105-S2',
    title: 'Kids & Women’s Garment Construction',
    objective: 'Designing, drafting and construction of Kids wear (A-line frock, Romper, Night suit) and Women’s wear (Petticoat, Blouse, Kameez, Salwar/Churidaar).',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },

  // ==================== SEMESTER 3 PRACTICALS ====================
  {
    practicalId: 'bvtd103_s3_p1',
    subjectCode: 'BVTD103-S3',
    title: 'Child & Adult Bodice Block Drafting',
    objective: 'Drafting child bodice block, sleeve block, and adult bodice/sleeve block using metric system measurement charts.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd103_s3_p2',
    subjectCode: 'BVTD103-S3',
    title: 'Drafting of Sleeves & Collars',
    objective: 'Drafting of circular, bishop, lantern, and saddle sleeves; drafting of bishop, coat, shawl, stand and fall, and convertible collars.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd103_s3_p3',
    subjectCode: 'BVTD103-S3',
    title: 'Drafting of Skirts & Contoured Patterns',
    objective: 'Drafting of basic skirt, wrap-around skirt, handkerchief skirt, and developing halter style and double-breasted contoured patterns.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd103_s3_p4',
    subjectCode: 'BVTD103-S3',
    title: 'Dart Manipulation by Pivot & Slash/Spread Methods',
    objective: 'Shifting of darts by pivot method and slash-and-spread method on full scale for single, two, and multiple dart series, princess lines, and yokes.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd103_s3_p5',
    subjectCode: 'BVTD103-S3',
    title: 'Pattern Grading of Bodice, Sleeve & Skirt',
    objective: 'Grading of adult bodice block, full sleeve, and skirt pattern across standard size specifications.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd105_s3_p1',
    subjectCode: 'BVTD105-S3',
    title: 'CAD Sloper Development (Child & Adult Block)',
    objective: 'Sloper development on CAD software for basic child block, basic sleeve, basic adult block, and grain line/dart manipulation.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd106_s3_p1',
    subjectCode: 'BVTD106-S3',
    title: 'Flat Sketching & Fabric Texture Rendering',
    objective: 'Flat sketching of pockets, sleeves, cuffs, necklines, collars, skirts, and rendering of net, cotton, silk, velvet, denim, and fur textures.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd107_s3_p1',
    subjectCode: 'BVTD107-S3',
    title: 'Theme-Based Women’s Dress Construction',
    objective: 'Design and construction of women dresses on themes: Indian traditional dress, Western party wear, Office wear, Night wear, and Casual wear.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },

  // ==================== SEMESTER 4 PRACTICALS ====================
  {
    practicalId: 'bvtd102_s4_p1',
    subjectCode: 'BVTD102-S4',
    title: 'Traditional Indian Embroidery Samples',
    objective: 'Sample making of Phulkari of Punjab, Kantha of Bengal, and Chikankari of Uttar Pradesh.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd102_s4_p2',
    subjectCode: 'BVTD102-S4',
    title: 'Dyeing & Printing Samples (Tie-Dye, Block & Screen)',
    objective: 'Dyeing and printing samples: Tie and Dye, Block printing, Screen printing, and sample file preparation for rugs and mats.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd103_s4_p1',
    subjectCode: 'BVTD103-S4',
    title: 'Draping Basic Bodice Block & Variations',
    objective: 'Draping of basic bodice block (front, back), classic princess line, cowl neckline, halter neck, French darts, and bustier draping.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd105_s4_p1',
    subjectCode: 'BVTD105-S4',
    title: 'CAD 10 Outfits Theme Design',
    objective: 'Designing 10 outfits on CAD software for themes: Season, Party wear, Sports, Casual, Beach, Club, Night, Evening, Uniform.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd106_s4_p1',
    subjectCode: 'BVTD106-S4',
    title: 'Professional Illustrations & Theme Specification Sheets',
    objective: 'Professional fashion illustrations on croquis with Mood Board, Colour Board, Swatch Board, Garment Specification sheet, and Costing sheet.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
  },
  {
    practicalId: 'bvtd107_s4_p1',
    subjectCode: 'BVTD107-S4',
    title: 'Men’s Wear Garment Construction',
    objective: 'Design and construct men’s dresses on any three themes: Casual wear, Street wear, Traditional Indian dress, Formal dress.',
    isOfficialSyllabusPractical: true,
    sourceLabel: 'Official University Syllabus'
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
