package com.example.data.repository

import com.example.data.model.*

object QuizFlashcardRepository {

    // =========================================================================
    // COMPREHENSIVE QUIZ QUESTIONS DATABASE (Scoped by Subject, Unit, & Topic)
    // =========================================================================
    val allQuestions: List<QuizQuestion> = listOf(
        // ---------------------------------------------------------------------
        // BVTD 111: Textile Science (Theory) - Unit 1: Introduction to Fibres
        // ---------------------------------------------------------------------
        QuizQuestion(
            id = "bvtd111_u1_q1",
            question = "Which natural vegetable fibre is extracted from the seed pod of the Gossypium plant?",
            options = listOf("Flax / Linen", "Cotton", "Jute", "Hemp"),
            correctIndex = 1,
            explanation = "Cotton is a natural unicellular vegetable seed-hair fibre composed primarily of cellulose (88-96%).",
            difficulty = QuizDifficulty.EASY,
            subjectCode = "BVTD111",
            unitNumber = 1,
            topicId = "bvtd111_u1_t1"
        ),
        QuizQuestion(
            id = "bvtd111_u1_q2",
            question = "During the burning test, which fibre burns with a smell of burning feathers/hair and leaves a dark, crushable bead?",
            options = listOf("Cotton", "Wool", "Nylon", "Polyester"),
            correctIndex = 1,
            explanation = "Wool is a natural animal protein fibre containing keratin and sulfur, giving off a characteristic burning hair odor with irregular, friable ash.",
            difficulty = QuizDifficulty.MEDIUM,
            subjectCode = "BVTD111",
            unitNumber = 1,
            topicId = "bvtd111_u1_t1"
        ),
        QuizQuestion(
            id = "bvtd111_u1_q3",
            question = "Under a compound microscope, cotton fibres exhibit which distinctive longitudinal morphology?",
            options = listOf("Smooth cylinder with striations", "Flat twisted ribbon with natural convolutions", "Scaly surface with serrations", "Cross markings resembling nodes"),
            correctIndex = 1,
            explanation = "Mature cotton fibres collapse upon drying into flat, ribbon-like tubes with spiraled reversals termed convolutions.",
            difficulty = QuizDifficulty.MEDIUM,
            subjectCode = "BVTD111",
            unitNumber = 1,
            topicId = "bvtd111_u1_t1"
        ),
        QuizQuestion(
            id = "bvtd111_u1_q4",
            question = "What is the standard moisture regain percentage of natural cotton at 65% RH and 20°C?",
            options = listOf("0.4%", "8.5%", "15.0%", "4.0%"),
            correctIndex = 1,
            explanation = "Commercial moisture regain for standard cotton is defined internationally as 8.5%.",
            difficulty = QuizDifficulty.HARD,
            subjectCode = "BVTD111",
            unitNumber = 1,
            topicId = "bvtd111_u1_t1"
        ),
        QuizQuestion(
            id = "bvtd111_u1_q5",
            question = "Which synthetic filament fibre is synthesized by polycondensation of terephthalic acid and ethylene glycol?",
            options = listOf("Nylon 6,6", "Polyester (PET)", "Acrylic", "Viscose Rayon"),
            correctIndex = 1,
            explanation = "Polyethylene terephthalate (PET) polyester is manufactured through the esterification and polycondensation of ethylene glycol and purified terephthalic acid.",
            difficulty = QuizDifficulty.HARD,
            subjectCode = "BVTD111",
            unitNumber = 1,
            topicId = "bvtd111_u1_t1"
        ),

        // ---------------------------------------------------------------------
        // BVTD 112: Sewing Techniques — Practical (PRACTICAL SUBJECT)
        // Unit 1: Machine Anatomy, Stitches, Seams & Fullness
        // ---------------------------------------------------------------------
        QuizQuestion(
            id = "bvtd112_u1_q1",
            question = "What is the ISO 4915 numerical stitch designation for the standard Single Needle Lockstitch (SNLS)?",
            options = listOf("Stitch Type 101", "Stitch Type 301", "Stitch Type 401", "Stitch Type 504"),
            correctIndex = 1,
            explanation = "Stitch 301 is the plain lockstitch, formed with two threads (needle thread and bobbin thread) interlacing in the center of the fabric plies.",
            difficulty = QuizDifficulty.EASY,
            isPracticalViva = true,
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1"
        ),
        QuizQuestion(
            id = "bvtd112_u1_q2",
            question = "If loops of loose thread appear continuously on the UNDERSIDE of the stitched fabric, what is the primary technical cause?",
            options = listOf("Bobbin tension is too tight", "Upper needle thread tension is too loose or unthreaded from take-up lever", "Needle is inserted backwards", "Presser foot pressure is too high"),
            correctIndex = 1,
            explanation = "Underside loops are caused by inadequate upper needle thread tension or the thread slipping out of the take-up lever / tension discs.",
            difficulty = QuizDifficulty.MEDIUM,
            isPracticalViva = true,
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1"
        ),
        QuizQuestion(
            id = "bvtd112_u1_q3",
            question = "Which seam construction encases all raw fabric allowances inside a second row of stitching, making it ideal for sheer fabrics like organza and chiffon?",
            options = listOf("Plain Seam with pinked finish", "Run & Fell (Flat-Felled) Seam", "French Seam", "Counter (Lapped) Seam"),
            correctIndex = 2,
            explanation = "A French seam starts with wrong sides together, is trimmed to 3mm, pressed, and re-stitched with right sides together to completely encase all raw edges.",
            difficulty = QuizDifficulty.EASY,
            isPracticalViva = true,
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1"
        ),
        QuizQuestion(
            id = "bvtd112_u1_q4",
            question = "In industrial lockstitch machines, which mechanical part lifts the thread from the spool and sets the knot into the fabric seam at every stitch cycle?",
            options = listOf("Presser bar lifter", "Thread take-up lever", "Feed dog rocker", "Shuttle race cover"),
            correctIndex = 1,
            explanation = "The thread take-up lever pulls thread through tension discs during needle descent and pulls it taut to set the interlocked stitch knot as the needle ascends.",
            difficulty = QuizDifficulty.MEDIUM,
            isPracticalViva = true,
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1"
        ),
        QuizQuestion(
            id = "bvtd112_u1_q5",
            question = "What needle size (Singer / Metric system) is recommended for stitching medium-weight cotton poplin and shirting fabric?",
            options = listOf("Size 9 / 65", "Size 11 / 75", "Size 14 / 90", "Size 18 / 110"),
            correctIndex = 2,
            explanation = "Metric size 90 (Singer 14) with a regular round point is standard for medium-weight cotton shirting and woven poplin.",
            difficulty = QuizDifficulty.MEDIUM,
            isPracticalViva = true,
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1"
        ),
        QuizQuestion(
            id = "bvtd112_u1_q6",
            question = "Which type of pleat consists of two single knife pleats turned toward each other, meeting in the center on the right side?",
            options = listOf("Accordion Pleat", "Inverted Box Pleat", "Box Pleat", "Kick Pleat"),
            correctIndex = 2,
            explanation = "A Box pleat has folds turned away from each other on the underside, forming an elevated rectangular panel on the face.",
            difficulty = QuizDifficulty.MEDIUM,
            isPracticalViva = true,
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1"
        ),
        QuizQuestion(
            id = "bvtd112_u1_q7",
            question = "What is the primary industrial application of the Run & Fell (Flat-Felled) seam?",
            options = listOf("Silk evening gown hems", "Inseams and outseams of denim jeans and workwear shirts", "Neckline piping", "Chiffon dupatta borders"),
            correctIndex = 1,
            explanation = "Run & Fell seam provides double structural topstitching with zero exposed raw edges on either side, essential for heavy denim and utility garments.",
            difficulty = QuizDifficulty.EASY,
            isPracticalViva = true,
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1"
        ),
        QuizQuestion(
            id = "bvtd112_u1_q8",
            question = "Which hand basting stitch is specifically recommended for securing slippery silk linings and heavy coat interfacings before machine stitching?",
            options = listOf("Even Basting", "Uneven Basting", "Diagonal (Tailor's) Basting", "Running Stitch"),
            correctIndex = 2,
            explanation = "Diagonal basting holds large fabric planes firmly against shifting without creating transverse tension or distortion.",
            difficulty = QuizDifficulty.HARD,
            isPracticalViva = true,
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1"
        ),

        // ---------------------------------------------------------------------
        // BVTD 113: Introduction to Entrepreneurship (Theory)
        // Unit 1, Unit 2, Unit 3, Unit 4
        // ---------------------------------------------------------------------
        QuizQuestion(
            id = "bvtd113_u1_q1",
            question = "According to Joseph Schumpeter, what is the defining primary function of an entrepreneur?",
            options = listOf("Routine administration of capital", "Carrying out new combinations (Innovation)", "Maintaining static market equilibrium", "Minimizing employee wages"),
            correctIndex = 1,
            explanation = "Schumpeter defined the entrepreneur as an innovator whose introduction of new products, processes, or markets drives economic development.",
            difficulty = QuizDifficulty.MEDIUM,
            subjectCode = "BVTD113",
            unitNumber = 1,
            topicId = "bvtd113_u1_t1"
        ),
        QuizQuestion(
            id = "bvtd113_u1_q2",
            question = "Which behavioral competency enables an entrepreneur to persist in goal achievement despite market setbacks?",
            options = listOf("External locus of control", "Internal locus of control & resilience", "Risk avoidance behavior", "Status-quo orientation"),
            correctIndex = 1,
            explanation = "An internal locus of control empowers entrepreneurs to believe their personal actions, skills, and decisions control business outcomes.",
            difficulty = QuizDifficulty.EASY,
            subjectCode = "BVTD113",
            unitNumber = 1,
            topicId = "bvtd113_u1_t1"
        ),
        QuizQuestion(
            id = "bvtd113_u2_q1",
            question = "Under the revised MSMED Act (effective from 2020), a 'Micro Enterprise' in manufacturing/apparel is defined by which investment and turnover criteria?",
            options = listOf("Investment <= 1 Cr and Turnover <= 5 Cr", "Investment <= 10 Cr and Turnover <= 50 Cr", "Investment <= 50 Cr and Turnover <= 250 Cr", "Investment <= 25 Lakhs and Turnover <= 1 Cr"),
            correctIndex = 0,
            explanation = "Under the composite criteria: Micro = Plant/Machinery <= ₹1 Crore and Annual Turnover <= ₹5 Crore.",
            difficulty = QuizDifficulty.HARD,
            subjectCode = "BVTD113",
            unitNumber = 2,
            topicId = "bvtd113_u2_t1"
        ),
        QuizQuestion(
            id = "bvtd113_u3_q1",
            question = "What is the primary objective of preparing a Comprehensive Detailed Project Report (DPR)?",
            options = listOf("Filing income tax audits only", "Evaluating technical feasibility, financial viability, and securing bank credit", "Calculating fabric consumption in cutting room", "Registering trademark logos"),
            correctIndex = 1,
            explanation = "A DPR assesses the technical, commercial, financial, and management viability of a proposed enterprise for bank appraisal and sanctioning.",
            difficulty = QuizDifficulty.MEDIUM,
            subjectCode = "BVTD113",
            unitNumber = 3,
            topicId = "bvtd113_u3_t1"
        ),
        QuizQuestion(
            id = "bvtd113_u4_q1",
            question = "Which apex statutory institutional body at the district level provides single-window clearances, machinery subsidies, and artisan credit support?",
            options = listOf("District Industries Centre (DIC)", "National Small Industries Corporation (NSIC)", "Khadi & Village Industries Board (KVIB)", "SIDBI"),
            correctIndex = 0,
            explanation = "District Industries Centres (DICs) operate under state directorates to offer single-window services to micro and small industrial units.",
            difficulty = QuizDifficulty.MEDIUM,
            subjectCode = "BVTD113",
            unitNumber = 4,
            topicId = "bvtd113_u4_t1"
        ),
        QuizQuestion(
            id = "bvtd113_u4_q2",
            question = "Under the Pradhan Mantri MUDRA Yojana, what is the credit ceiling under the 'Kishore' category for expanding boutique enterprises?",
            options = listOf("Up to ₹50,000", "Above ₹50,000 and up to ₹5 Lakhs", "Above ₹5 Lakhs and up to ₹10 Lakhs", "Above ₹10 Lakhs up to ₹25 Lakhs"),
            correctIndex = 1,
            explanation = "Mudra categories: Shishu (up to ₹50,000), Kishore (₹50,000 to ₹5 Lakhs), and Tarun (₹5 Lakhs to ₹10 Lakhs).",
            difficulty = QuizDifficulty.HARD,
            subjectCode = "BVTD113",
            unitNumber = 4,
            topicId = "bvtd113_u4_t1"
        ),

        // ---------------------------------------------------------------------
        // BVTD 121: Introduction to Fashion (Theory) - Unit 1: Fashion Life Cycle
        // ---------------------------------------------------------------------
        QuizQuestion(
            id = "bvtd121_u1_q1",
            question = "What is the chronological sequence of the 5 stages in the Fashion Life Cycle curve?",
            options = listOf(
                "Rise -> Introduction -> Culmination -> Decline -> Obsolescence",
                "Introduction -> Rise -> Culmination (Peak) -> Decline -> Obsolescence",
                "Creation -> Adoption -> Peak -> Reproduction -> Discard",
                "Introduction -> Plateau -> Rise -> Clearance -> Discontinuation"
            ),
            correctIndex = 1,
            explanation = "The standard fashion cycle follows the 5 stages: Introduction (high price/low vol) -> Rise -> Culmination/Peak (mass acceptance) -> Decline -> Obsolescence.",
            difficulty = QuizDifficulty.EASY,
            subjectCode = "BVTD121",
            unitNumber = 1,
            topicId = "bvtd121_u1_t1"
        ),
        QuizQuestion(
            id = "bvtd121_u1_q2",
            question = "A fashion style characterized by rapid adoption, intense short-lived popularity, and sudden disappearance within weeks is classified as a:",
            options = listOf("Classic", "Fad", "Trend", "Avant-Garde"),
            correctIndex = 1,
            explanation = "A 'Fad' is a short-lived craze with a steep bell curve that lacks the design integrity to become a lasting trend or classic.",
            difficulty = QuizDifficulty.EASY,
            subjectCode = "BVTD121",
            unitNumber = 1,
            topicId = "bvtd121_u1_t1"
        ),
        QuizQuestion(
            id = "bvtd121_u1_q3",
            question = "Which fashion adoption theory proposes that styles originate among subcultures and street fashion before being adopted by high-fashion couturiers?",
            options = listOf("Trickle-Down Theory", "Trickle-Up (Bottom-Up) Theory", "Trickle-Across Theory", "Mass Market Theory"),
            correctIndex = 1,
            explanation = "Trickle-Up theory explains how street trends (e.g. distressed denim, punk aesthetic, sneakers) climb into luxury couture collections.",
            difficulty = QuizDifficulty.MEDIUM,
            subjectCode = "BVTD121",
            unitNumber = 1,
            topicId = "bvtd121_u1_t1"
        ),

        // ---------------------------------------------------------------------
        // BVTD 122: Garment Sewing — Practical (PRACTICAL SUBJECT)
        // Unit 1: Pattern Drafting & Bodice Block
        // ---------------------------------------------------------------------
        QuizQuestion(
            id = "bvtd122_u1_q1",
            question = "In pattern drafting, what is the anatomical pivot point for all basic bodice dart manipulations?",
            options = listOf("Shoulder tip", "Bust Apex point", "Mid-armhole notch", "Waist side seam"),
            correctIndex = 1,
            explanation = "The bust apex is the highest protrusion mound around which excess fabric suppression (darts) rotates without altering garment fit volume.",
            difficulty = QuizDifficulty.EASY,
            isPracticalViva = true,
            subjectCode = "BVTD122",
            unitNumber = 1,
            topicId = "bvtd122_t1"
        ),
        QuizQuestion(
            id = "bvtd122_u1_q2",
            question = "Which drafting tool is essential for drawing smooth anatomical curves along the armscye, neckline, and crotch line?",
            options = listOf("L-Square / Tailor's Square", "French Curve", "Yardstick", "Tracing Wheel"),
            correctIndex = 1,
            explanation = "A clear acrylic French curve provides graded ellipses for drafting anatomical body contours.",
            difficulty = QuizDifficulty.EASY,
            isPracticalViva = true,
            subjectCode = "BVTD122",
            unitNumber = 1,
            topicId = "bvtd122_t1"
        ),
        QuizQuestion(
            id = "bvtd122_u1_q3",
            question = "What is the two-method technique used to rotate a waist dart into a shoulder dart without redrafting the foundation block?",
            options = listOf("Slash-and-spread method & Pivot method", "Tucking method & Pleating method", "Pressing method & Ironing method", "Grade ruler method"),
            correctIndex = 0,
            explanation = "Both the Slash-and-Spread method and the Pivot method relocate dart intake cleanly around the bust apex.",
            difficulty = QuizDifficulty.MEDIUM,
            isPracticalViva = true,
            subjectCode = "BVTD122",
            unitNumber = 1,
            topicId = "bvtd122_t1"
        ),
        QuizQuestion(
            id = "bvtd122_u1_q4",
            question = "When attaching a concealed invisible zipper on a fitted garment, which specialized machine attachment is required?",
            options = listOf("Walking foot", "Invisible zipper foot with parallel bottom grooves", "Roller foot", "Ruffler attachment"),
            correctIndex = 1,
            explanation = "An invisible zipper foot has dual underside parallel grooves that unroll the zipper coil so needle stitches land right along the tape line.",
            difficulty = QuizDifficulty.MEDIUM,
            isPracticalViva = true,
            subjectCode = "BVTD122",
            unitNumber = 1,
            topicId = "bvtd122_t1"
        ),

        // ---------------------------------------------------------------------
        // BVTD 123: Textile Design & Weaving — Practical (PRACTICAL SUBJECT)
        // Unit 1: Weave Construction & Interlacement Graphing
        // ---------------------------------------------------------------------
        QuizQuestion(
            id = "bvtd123_u1_q1",
            question = "What is the simplest fundamental weave structure characterized by an alternate 1/1 'over one, under one' interlacement?",
            options = listOf("Plain Weave (Tabby)", "Twill Weave", "Satin Weave", "Jacquard Weave"),
            correctIndex = 0,
            explanation = "Plain weave (1/1) has the maximum number of yarn interlacements per unit area, giving high structural stability.",
            difficulty = QuizDifficulty.EASY,
            isPracticalViva = true,
            subjectCode = "BVTD123",
            unitNumber = 1,
            topicId = "bvtd123_t1"
        ),
        QuizQuestion(
            id = "bvtd123_u1_q2",
            question = "Which weave is identified by prominent diagonal lines (wales) running across the fabric face at a 45-degree angle?",
            options = listOf("Plain Weave", "2/2 Twill Weave", "5-end Sateen", "Basket Weave"),
            correctIndex = 1,
            explanation = "Twill weave is characterized by diagonal ridges (wales) formed by stepping warp floats by one pick in successive rows.",
            difficulty = QuizDifficulty.EASY,
            isPracticalViva = true,
            subjectCode = "BVTD123",
            unitNumber = 1,
            topicId = "bvtd123_t1"
        ),
        QuizQuestion(
            id = "bvtd123_u1_q3",
            question = "In weave analysis and point paper drafting, what does a filled square on graph paper traditionally represent?",
            options = listOf("Weft yarn over Warp yarn", "Warp yarn over Weft yarn (Warp float)", "Missing pick defect", "Selvedge cord"),
            correctIndex = 1,
            explanation = "In standard textile graphing convention, a marked/filled point indicates the warp yarn floats over the weft pick.",
            difficulty = QuizDifficulty.MEDIUM,
            isPracticalViva = true,
            subjectCode = "BVTD123",
            unitNumber = 1,
            topicId = "bvtd123_t1"
        ),
        QuizQuestion(
            id = "bvtd123_u1_q4",
            question = "What is the minimum number of heald frames (shafts) required to weave a standard 2/2 Twill structure?",
            options = listOf("2 shafts", "4 shafts", "6 shafts", "8 shafts"),
            correctIndex = 1,
            explanation = "A 2/2 twill has a repeat size of 4 warp threads and 4 weft picks, requiring a minimum of 4 heald shafts in straight drafting.",
            difficulty = QuizDifficulty.HARD,
            isPracticalViva = true,
            subjectCode = "BVTD123",
            unitNumber = 1,
            topicId = "bvtd123_t1"
        ),

        // ---------------------------------------------------------------------
        // ZDA 111: Drug Abuse (Theory) - Unit 1: Awareness & Prevention
        // ---------------------------------------------------------------------
        QuizQuestion(
            id = "zda111_u1_q1",
            question = "Which institutional approach is most effective for community-based youth substance abuse prevention?",
            options = listOf("Punitive isolation", "Early education, parental counseling, and peer support networks", "Market deregulations", "Ignoring initial signs"),
            correctIndex = 1,
            explanation = "Preventive education and family/peer counseling provide protective psychological barriers against substance abuse.",
            difficulty = QuizDifficulty.EASY,
            subjectCode = "ZDA111",
            unitNumber = 1,
            topicId = "zda111_t1"
        )
    )

    // =========================================================================
    // COMPREHENSIVE FLASHCARDS DATABASE (Categorized & Scoped by Subject/Unit)
    // =========================================================================
    val allFlashcards: List<FlashcardItem> = listOf(
        // ---------------------------------------------------------------------
        // BVTD 112: Sewing Techniques — Practical (PRACTICAL FLASHCARDS)
        // ---------------------------------------------------------------------
        FlashcardItem(
            id = "fc_bvtd112_1",
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1",
            type = FlashcardType.IDENTIFICATION,
            front = "What is a Bobbin & Bobbin Case in an Industrial SNLS Machine?",
            back = "The bobbin holds the lower underthread. The bobbin case encases the bobbin, controls bottom thread tension with an adjustable leaf spring screw, and fits inside the rotary hook assembly.",
            categoryHint = "Machine Anatomy & Tools",
            practicalTag = "Equipment"
        ),
        FlashcardItem(
            id = "fc_bvtd112_2",
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1",
            type = FlashcardType.PRACTICAL,
            front = "How do you solve Upper Thread Breakage during high-speed sewing?",
            back = "1. Check if needle is blunt, bent, or inserted backwards.\n2. Loosen upper tension discs.\n3. Verify thread is properly threaded through take-up lever.\n4. Check for burrs/grooves on the throat plate needle hole.",
            categoryHint = "Troubleshooting & Maintenance",
            practicalTag = "Troubleshooting"
        ),
        FlashcardItem(
            id = "fc_bvtd112_3",
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1",
            type = FlashcardType.PROCESS,
            front = "What are the exact steps to construct a French Seam?",
            back = "Step 1: Place fabric WRONG sides together and stitch 6mm (1/4\") from edge.\nStep 2: Trim raw allowance neatly to 3mm (1/8\").\nStep 3: Press seam flat, then fold RIGHT sides together along stitch line.\nStep 4: Stitch 6mm from fold edge, completely enclosing all raw threads.",
            categoryHint = "Seam Engineering",
            practicalTag = "Procedure"
        ),
        FlashcardItem(
            id = "fc_bvtd112_4",
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1",
            type = FlashcardType.VIVA,
            front = "Viva Voce: Why is Flat-Felled (Run & Fell) Seam used on denim jeans?",
            back = "Because it is a double topstitched, completely enclosed flat seam with extraordinary tensile strength. It withstands heavy wash treatments and abrasion while remaining smooth against the skin.",
            categoryHint = "Viva Voce & Industry Applications",
            practicalTag = "Viva"
        ),
        FlashcardItem(
            id = "fc_bvtd112_5",
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1",
            type = FlashcardType.IDENTIFICATION,
            front = "What is the Feed Dog mechanism and why must it be cleaned?",
            back = "The feed dog has serrated teeth that rise through the throat plate to advance fabric forward by one stitch length per cycle. Accumulated lint under the throat plate prevents proper teeth elevation, causing stitch skipping and puckering.",
            categoryHint = "Machine Anatomy",
            practicalTag = "Equipment"
        ),
        FlashcardItem(
            id = "fc_bvtd112_6",
            subjectCode = "BVTD112",
            unitNumber = 1,
            topicId = "bvtd112_t1",
            type = FlashcardType.DEFINITION,
            front = "What is a Dart in garment construction?",
            back = "A triangular folded wedge stitched into flat fabric to convert a two-dimensional sheet into a three-dimensional shape conforming to anatomical body contours (bust, waist, hips).",
            categoryHint = "Fullness Control",
            practicalTag = "Terminology"
        ),

        // ---------------------------------------------------------------------
        // BVTD 111: Textile Science (Theory Flashcards)
        // ---------------------------------------------------------------------
        FlashcardItem(
            id = "fc_bvtd111_1",
            subjectCode = "BVTD111",
            unitNumber = 1,
            topicId = "bvtd111_u1_t1",
            type = FlashcardType.DEFINITION,
            front = "What is a Textile Fibre?",
            back = "A unit of matter characterized by flexibility, fineness, and a high ratio of length to thickness (at least 100:1), capable of being spun into yarn and woven/knitted into fabric.",
            categoryHint = "Fundamentals",
            practicalTag = "Theory"
        ),
        FlashcardItem(
            id = "fc_bvtd111_2",
            subjectCode = "BVTD111",
            unitNumber = 1,
            topicId = "bvtd111_u1_t1",
            type = FlashcardType.IDENTIFICATION,
            front = "How do you differentiate Cotton vs Polyester by Burning Test?",
            back = "Cotton: Ignites quickly, smells like burning paper, produces lightweight grey ash, no melting.\nPolyester: Shrinks from flame, melts into a hard chemical round bead, emits sweet chemical odor.",
            categoryHint = "Lab Identification",
            practicalTag = "Testing"
        ),
        FlashcardItem(
            id = "fc_bvtd111_3",
            subjectCode = "BVTD111",
            unitNumber = 1,
            topicId = "bvtd111_u1_t1",
            type = FlashcardType.CONCEPT,
            front = "Why does Wool possess natural warmth and resiliency?",
            back = "Wool fibres have 3D natural crimp and a cellular cortex that traps stagnant air pockets (providing thermal insulation). Its helical keratin protein chains return to their original shape after bending.",
            categoryHint = "Fibre Properties",
            practicalTag = "Theory"
        ),

        // ---------------------------------------------------------------------
        // BVTD 113: Introduction to Entrepreneurship (Theory Flashcards)
        // ---------------------------------------------------------------------
        FlashcardItem(
            id = "fc_bvtd113_1",
            subjectCode = "BVTD113",
            unitNumber = 1,
            topicId = "bvtd113_u1_t1",
            type = FlashcardType.DEFINITION,
            front = "Define Entrepreneurship in the Apparel Sector",
            back = "The dynamic process of identifying a fashion market opportunity, assembling capital, machinery, and design talent, and managing risk to build a profitable fashion venture.",
            categoryHint = "Entrepreneurship Basics",
            practicalTag = "Theory"
        ),
        FlashcardItem(
            id = "fc_bvtd113_2",
            subjectCode = "BVTD113",
            unitNumber = 2,
            topicId = "bvtd113_u2_t1",
            type = FlashcardType.CONCEPT,
            front = "What is the 2020 Revised MSME Definition for Small Enterprise?",
            back = "Investment in Plant & Machinery <= ₹10 Crore AND Annual Turnover <= ₹50 Crore (applies uniformly to both manufacturing and service sectors).",
            categoryHint = "MSME Policy",
            practicalTag = "Policy"
        ),
        FlashcardItem(
            id = "fc_bvtd113_3",
            subjectCode = "BVTD113",
            unitNumber = 4,
            topicId = "bvtd113_u4_t1",
            type = FlashcardType.CONCEPT,
            front = "What support does the District Industries Centre (DIC) provide to textile startups?",
            back = "1. Udyam Registration assistance.\n2. Prime Minister Employment Generation Programme (PMEGP) loan subsidies.\n3. Industrial plot allotments.\n4. Artisan exhibitions and skill training programmes.",
            categoryHint = "Institutional Support",
            practicalTag = "Policy"
        ),

        // ---------------------------------------------------------------------
        // BVTD 121: Introduction to Fashion (Theory Flashcards)
        // ---------------------------------------------------------------------
        FlashcardItem(
            id = "fc_bvtd121_1",
            subjectCode = "BVTD121",
            unitNumber = 1,
            topicId = "bvtd121_u1_t1",
            type = FlashcardType.DEFINITION,
            front = "Differentiate between a 'Classic' and a 'Fad'",
            back = "Classic: A style that maintains enduring consumer acceptance over decades with minor updates (e.g., Blazer, Trench Coat, Blue Jeans).\nFad: A short-lived craze that spikes rapidly and disappears within weeks.",
            categoryHint = "Fashion Theory",
            practicalTag = "Terminology"
        ),
        FlashcardItem(
            id = "fc_bvtd121_2",
            subjectCode = "BVTD121",
            unitNumber = 1,
            topicId = "bvtd121_u1_t1",
            type = FlashcardType.CONCEPT,
            front = "What happens at the 'Culmination (Peak)' stage of the Fashion Cycle?",
            back = "The design achieves maximum mass-market penetration, is manufactured at peak volume by mass retailers at accessible price points, and is worn by the general public.",
            categoryHint = "Fashion Life Cycle",
            practicalTag = "Theory"
        ),

        // ---------------------------------------------------------------------
        // BVTD 122: Garment Sewing — Practical (PRACTICAL FLASHCARDS)
        // ---------------------------------------------------------------------
        FlashcardItem(
            id = "fc_bvtd122_1",
            subjectCode = "BVTD122",
            unitNumber = 1,
            topicId = "bvtd122_t1",
            type = FlashcardType.PRACTICAL,
            front = "What is the Apex in Bodice Pattern Drafting?",
            back = "The most prominent point of the bust mound on the dress form/body. All foundation darts radiate towards and pivot through this central apex landmark.",
            categoryHint = "Pattern Drafting",
            practicalTag = "Drafting"
        ),
        FlashcardItem(
            id = "fc_bvtd122_2",
            subjectCode = "BVTD122",
            unitNumber = 1,
            topicId = "bvtd122_t1",
            type = FlashcardType.VIVA,
            front = "Viva Voce: Why must seam allowance be added to pattern draft edges?",
            back = "A working pattern without seam allowances produces an undersized garment after sewing. Standard industry seam allowance is 1.5cm (5/8\") for main seams and 0.6cm (1/4\") for enclosed necklines.",
            categoryHint = "Pattern Making Viva",
            practicalTag = "Viva"
        ),

        // ---------------------------------------------------------------------
        // BVTD 123: Textile Design & Weaving — Practical (PRACTICAL FLASHCARDS)
        // ---------------------------------------------------------------------
        FlashcardItem(
            id = "fc_bvtd123_1",
            subjectCode = "BVTD123",
            unitNumber = 1,
            topicId = "bvtd123_t1",
            type = FlashcardType.IDENTIFICATION,
            front = "How is a 2/2 Twill weave represented on design point paper?",
            back = "A 4x4 grid where row 1 is '2 up, 2 down' (XX--), and each subsequent pick shifts to the right by one square (row 2: -XX-, row 3: --XX, row 4: X--X), creating diagonal continuous twill wales.",
            categoryHint = "Weave Graphing",
            practicalTag = "Weaving"
        ),
        FlashcardItem(
            id = "fc_bvtd123_2",
            subjectCode = "BVTD123",
            unitNumber = 1,
            topicId = "bvtd123_t1",
            type = FlashcardType.VIVA,
            front = "Viva Voce: What are the three primary motions of a weaving loom?",
            back = "1. Shedding: Separating warp threads into upper and lower sheets.\n2. Picking: Inserting the weft yarn (shuttle / rapiers / air jet) through the shed.\n3. Beating-up: Pushing the newly inserted pick firmly into the fabric fell with the reed.",
            categoryHint = "Weaving Technology Viva",
            practicalTag = "Viva"
        )
    )

    // =========================================================================
    // SCOPE-AWARE RETRIEVAL FUNCTIONS
    // =========================================================================

    /**
     * Strictly scopes quiz questions to the selected Semester, Subject, Unit, Topic,
     * Difficulty, and Quiz Mode. Never leaks questions from other scopes!
     */
    fun getScopedQuestions(scope: QuizScopeSelection): List<QuizQuestion> {
        val cleanSubjectCode = scope.subjectCode.replace(" ", "").replace("-", "").uppercase()

        // Filter all candidate questions from repository plus topic-level embeds
        var candidateList = allQuestions.filter { q ->
            q.subjectCode.replace(" ", "").replace("-", "").uppercase() == cleanSubjectCode
        }

        // Apply Unit filtering if selected
        if (scope.scopeType == QuizScopeType.SPECIFIC_UNIT && scope.unitNumber != null) {
            candidateList = candidateList.filter { it.unitNumber == scope.unitNumber }
        } else if (scope.scopeType == QuizScopeType.SPECIFIC_TOPIC && !scope.topicId.isNullOrBlank()) {
            candidateList = candidateList.filter { it.topicId == scope.topicId }
        }

        // Apply Mode-based filtering
        if (scope.quizMode == QuizMode.VIVA) {
            val vivaOnly = candidateList.filter { it.isPracticalViva }
            if (vivaOnly.isNotEmpty()) {
                candidateList = vivaOnly
            }
        }

        // Apply Difficulty filtering if not MIXED
        if (scope.difficulty != QuizDifficulty.MIXED) {
            val difficultyMatches = candidateList.filter { it.difficulty == scope.difficulty }
            if (difficultyMatches.isNotEmpty()) {
                candidateList = difficultyMatches
            }
        }

        // If candidate list is smaller than requested, supplement strictly from this scope's syllabus topic terms
        if (candidateList.size < scope.questionCount) {
            val generated = generateScopedSyllabusQuestions(scope)
            val combined = (candidateList + generated).distinctBy { it.question }
            candidateList = combined
        }

        val shuffled = candidateList.shuffled()
        val targetCount = if (scope.quizMode == QuizMode.QUICK) 5 else scope.questionCount
        return shuffled.take(minOf(targetCount, shuffled.size))
    }

    /**
     * Strictly scopes flashcards to the selected Semester, Subject, Unit, Topic,
     * and optional FlashcardType.
     */
    fun getScopedFlashcards(
        semesterNumber: Int,
        subjectCode: String,
        unitNumber: Int? = null,
        topicId: String? = null,
        filterType: FlashcardType? = null
    ): List<FlashcardItem> {
        val cleanCode = subjectCode.replace(" ", "").replace("-", "").uppercase()
        var cards = allFlashcards.filter {
            it.subjectCode.replace(" ", "").replace("-", "").uppercase() == cleanCode
        }

        if (unitNumber != null && unitNumber > 0) {
            cards = cards.filter { it.unitNumber == unitNumber }
        }

        if (!topicId.isNullOrBlank()) {
            cards = cards.filter { it.topicId == topicId }
        }

        if (filterType != null) {
            cards = cards.filter { it.type == filterType }
        }

        // If cards list is sparse, generate contextual flashcards from official subject syllabus topics
        if (cards.size < 4) {
            val generated = generateScopedSyllabusFlashcards(subjectCode, unitNumber, topicId)
            val combined = (cards + generated).distinctBy { it.front }
            return combined
        }

        return cards
    }

    // =========================================================================
    // CONTEXTUAL SCOPE-AWARE QUESTION & FLASHCARD SYNTHESIS
    // (Preserves strict scope boundaries using real syllabus units & topics)
    // =========================================================================

    private fun generateScopedSyllabusQuestions(scope: QuizScopeSelection): List<QuizQuestion> {
        val subject = SyllabusRepository.getSubjectByCode(scope.subjectCode) ?: return emptyList()
        val generatedList = mutableListOf<QuizQuestion>()

        val targetUnits = when (scope.scopeType) {
            QuizScopeType.ENTIRE_SUBJECT -> subject.units
            QuizScopeType.SPECIFIC_UNIT -> subject.units.filter { it.unitNumber == scope.unitNumber }
            QuizScopeType.SPECIFIC_TOPIC -> subject.units.mapNotNull { u ->
                val matchingTopics = u.topics.filter { it.id == scope.topicId }
                if (matchingTopics.isNotEmpty()) u.copy(topics = matchingTopics) else null
            }
        }

        for (unit in targetUnits) {
            for (topic in unit.topics) {
                // Generate from key terms
                topic.importantTerms.forEach { (term, definition) ->
                    generatedList.add(
                        QuizQuestion(
                            id = "gen_${topic.id}_${term.hashCode()}",
                            question = "In the study of ${topic.title}, what is the accurate academic definition of '$term'?",
                            options = listOf(
                                definition,
                                "A superficial embellishment technique used without functional significance in textile mills.",
                                "A temporary defect arising solely during chemical finishing operations.",
                                "An outdated terminology replaced in current curriculum standards."
                            ),
                            correctIndex = 0,
                            explanation = "According to syllabus curriculum specifications: $term is defined as $definition.",
                            difficulty = QuizDifficulty.MEDIUM,
                            subjectCode = subject.code,
                            unitNumber = unit.unitNumber,
                            topicId = topic.id
                        )
                    )
                }

                // Generate from practicals if present
                for (practical in subject.practicals) {
                    practical.vivaQuestions.forEachIndexed { vIdx, (vQ, vA) ->
                        generatedList.add(
                            QuizQuestion(
                                id = "gen_prac_${practical.id}_$vIdx",
                                question = vQ,
                                options = listOf(
                                    vA,
                                    "It is determined solely by visual inspection without calibration.",
                                    "It varies arbitrarily without technical standards.",
                                    "No specific precaution is required under laboratory guidelines."
                                ),
                                correctIndex = 0,
                                explanation = "Laboratory & Viva Voce Standard: $vA",
                                difficulty = QuizDifficulty.HARD,
                                isPracticalViva = true,
                                subjectCode = subject.code,
                                unitNumber = unit.unitNumber,
                                topicId = topic.id
                            )
                        )
                    }
                }
            }
        }

        return generatedList
    }

    private fun generateScopedSyllabusFlashcards(
        subjectCode: String,
        unitNumber: Int?,
        topicId: String?
    ): List<FlashcardItem> {
        val subject = SyllabusRepository.getSubjectByCode(subjectCode) ?: return emptyList()
        val cards = mutableListOf<FlashcardItem>()

        val units = if (unitNumber != null) subject.units.filter { it.unitNumber == unitNumber } else subject.units

        for (unit in units) {
            val topics = if (!topicId.isNullOrBlank()) unit.topics.filter { it.id == topicId } else unit.topics
            for (topic in topics) {
                topic.importantTerms.forEach { (term, def) ->
                    cards.add(
                        FlashcardItem(
                            id = "fc_gen_${topic.id}_${term.hashCode()}",
                            subjectCode = subject.code,
                            unitNumber = unit.unitNumber,
                            topicId = topic.id,
                            type = FlashcardType.DEFINITION,
                            front = "Define '$term' in ${topic.title}",
                            back = def,
                            categoryHint = "${subject.code} • Unit ${unit.unitNumber}",
                            practicalTag = if (subject.type == SubjectType.PRACTICAL) "Practical Lab" else "Syllabus Concept"
                        )
                    )
                }

                if (topic.keyPoints.isNotEmpty()) {
                    cards.add(
                        FlashcardItem(
                            id = "fc_gen_key_${topic.id}",
                            subjectCode = subject.code,
                            unitNumber = unit.unitNumber,
                            topicId = topic.id,
                            type = FlashcardType.CONCEPT,
                            front = "What are the core principles of ${topic.title}?",
                            back = topic.keyPoints.joinToString("\n• ", prefix = "• "),
                            categoryHint = "${subject.code} Key Concepts",
                            practicalTag = "Core Theory"
                        )
                    )
                }
            }
        }

        // Add practical activities if practical subject
        for (prac in subject.practicals) {
            prac.vivaQuestions.forEachIndexed { idx, (q, a) ->
                cards.add(
                    FlashcardItem(
                        id = "fc_gen_viva_${prac.id}_$idx",
                        subjectCode = subject.code,
                        unitNumber = 1,
                        topicId = "bvtd112_t1",
                        type = FlashcardType.VIVA,
                        front = "Viva Voce: $q",
                        back = a,
                        categoryHint = "${prac.title} (Lab Exam)",
                        practicalTag = "Viva Voce"
                    )
                )
            }
        }

        return cards
    }
}
