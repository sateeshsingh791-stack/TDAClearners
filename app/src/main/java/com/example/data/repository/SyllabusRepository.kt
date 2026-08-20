package com.example.data.repository

import com.example.data.model.*

object SyllabusRepository {

    val collegeInfo = mapOf(
        "collegeName" to "Khalsa College, Amritsar",
        "department" to "Department of Fashion Designing & Textile Technology",
        "courseName" to "B.Voc Textile Design & Apparel Technology",
        "courseType" to "Vocational Degree (UGC Recognized / GNDU Aligned)",
        "duration" to "3 Years (6 Semesters)",
        "syllabusVersion" to "Current 1st Year NEP Syllabus Scheme"
    )

    private val sem1PracticalsBvtd112 = listOf(
        PracticalActivity(
            id = "bvtd112_p1",
            title = "Identification of Fibres by Burning Test",
            subjectCode = "BVTD 111/112",
            objective = "To identify cotton, wool, silk, nylon, and polyester fibres through flame reaction, odor, and residue analysis.",
            materialsRequired = listOf("Fibre samples (Cotton, Silk, Wool, Polyester, Nylon)", "Spirit lamp / Bunsen burner", "Tweezers", "Dissecting needle", "Ceramic tile"),
            stepByStepProcedure = listOf(
                "Hold a small tuft of fibres with tweezers and bring it gradually near the side of the flame.",
                "Observe whether the sample melts, curls away, or ignites immediately.",
                "Move the sample into the flame and observe burning rate and smoke color.",
                "Remove sample from flame and observe if it continues to burn or self-extinguishes.",
                "Wafe the fumes gently to test odor (burning paper, burning hair, celery/chemical).",
                "Cool residue and test texture (crushable ash vs hard uncrushable bead)."
            ),
            expectedObservations = "Cotton burns with steady yellow flame and paper smell leaving grey ash. Wool burns slowly with burning hair smell leaving black crushable bead. Polyester melts and leaves a hard, black, uncrushable bead.",
            precautions = listOf("Work in a well-ventilated area.", "Never inhale fumes directly.", "Use tweezers to avoid finger burns."),
            vivaQuestions = listOf(
                "Why does wool smell like burning hair?" to "Wool is a protein fibre containing keratin and sulfur.",
                "How do synthetic fibres react near flame?" to "Synthetic fibres are thermoplastic; they shrink and melt before igniting.",
                "Can a burning test differentiate between cotton and linen?" to "No, both are cellulosic fibres with identical combustion characteristics. Microscopic test is needed."
            )
        ),
        PracticalActivity(
            id = "bvtd112_p2",
            title = "Color Wheel & Value Scale Development",
            subjectCode = "BVTD 111/112",
            objective = "To paint a 12-hue Prang color wheel and demonstrate tints, tones, and shades for textile motif palettes.",
            materialsRequired = listOf("Cartridge paper / Ivory sheet", "Poster paints (Primary: Red, Yellow, Blue)", "Synthetic round brushes (#2, #4, #6)", "Compass and ruler"),
            stepByStepProcedure = listOf(
                "Draw concentric circles divided into 12 equal sectors (30 degrees each).",
                "Apply pure primary colors: Red, Yellow, and Blue at equidistant points.",
                "Mix equal proportions of primaries to paint Secondary colors: Orange, Green, and Violet.",
                "Mix primary + adjacent secondary to obtain 6 Tertiary colors.",
                "Create a 7-step Value Scale (adding White for tints and Black for shades)."
            ),
            expectedObservations = "Smooth color transitions without streakiness, demonstrating chromatic harmony and color temperature principles.",
            precautions = listOf("Wash brushes thoroughly between color mixing.", "Maintain uniform paint consistency to avoid cracking."),
            vivaQuestions = listOf(
                "What is the difference between Tint and Shade?" to "A tint is a hue + white; a shade is a hue + black.",
                "What are complementary colors?" to "Colors located directly opposite each other on the color wheel (e.g., Red & Green)."
            )
        )
    )

    private val sem1PracticalsBvtd113 = listOf(
        PracticalActivity(
            id = "bvtd113_p1",
            title = "Machine Practice & Seam Samples",
            subjectCode = "BVTD 113",
            objective = "To prepare sample swatches of Plain Seam, French Seam, Flat-Felled Seam, and Bound Seam on cotton fabric.",
            materialsRequired = listOf("Single needle lockstitch machine", "Muslin / Cambric cotton fabric", "Sewing thread (40/2)", "Fabric shears", "Measuring tape & Iron"),
            stepByStepProcedure = listOf(
                "Thread the sewing machine, regulate upper tension and bobbin tension for balanced stitch.",
                "Cut fabric strips measuring 10cm x 15cm.",
                "Construct Plain Seam with 1.5cm seam allowance and press open.",
                "Construct French Seam with two rows of stitching (neat concealed raw edges).",
                "Construct Flat-Felled Seam used in denim and shirt side seams.",
                "Construct Bound Seam using bias binding tape."
            ),
            expectedObservations = "Balanced stitch (8-10 SPI) with no puckering or skipped stitches; clean corner and seam alignments.",
            precautions = listOf("Keep fingers away from the needle guard during high-speed stitching.", "Ensure needle size matches fabric weight (Size 14 for medium cotton)."),
            vivaQuestions = listOf(
                "What is SPI?" to "Stitches Per Inch, a key quality metric in garment manufacturing.",
                "Where is a French seam ideally used?" to "On sheer, lightweight, or delicate fabrics where raw edges fray easily."
            )
        )
    )

    private val semester1Subjects = listOf(
        Subject(
            code = "BVTD 111",
            name = "Design Foundation & Basics of Textile",
            semesterNumber = 1,
            category = CourseCategory.MAJOR,
            type = SubjectType.THEORY,
            hoursPerWeek = 1,
            lectureCredits = 2,
            tutorialCredits = 0,
            practicalCredits = 0,
            totalCredits = 2,
            theoryMarks = 37,
            practicalMarks = null,
            internalAssessmentMarks = 13,
            totalMarks = 50,
            syllabusPageRef = "Page 10",
            overview = "Foundational theory covering design principles, color theory, classification of textile fibres, and basic textile terminology essential for vocational designers.",
            learningObjectives = listOf(
                "Understand fundamental elements and principles of design.",
                "Classify natural and man-made textile fibres.",
                "Analyze yarn formation and numbering systems.",
                "Apply color harmonies in textile collection developments."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Elements & Principles of Design",
                    description = "Core visual vocabulary including line, form, space, balance, rhythm, harmony, and proportion.",
                    topics = listOf(
                        TopicContent(
                            id = "bvtd111_u1_t1",
                            title = "Elements of Design: Line, Shape, Texture & Color",
                            unitNumber = 1,
                            overview = "The building blocks of visual design. In textile design, lines create stripes and silhouettes, textures define fabric hand-feel, and color evokes emotional response.",
                            keyPoints = listOf(
                                "Lines can be structural or decorative, imparting optical illusions (vertical adds height, horizontal adds width).",
                                "Shapes are 2D geometric or organic; Forms possess 3D volume in draping.",
                                "Texture can be tactile (physical weave surface) or visual (printed pattern).",
                                "Color includes Hue, Value (lightness), and Chroma/Intensity (saturation)."
                            ),
                            importantTerms = mapOf(
                                "Hue" to "The pure name of a color in the spectrum.",
                                "Value" to "The lightness or darkness of a hue relative to black and white.",
                                "Chroma" to "The purity, vividness, or saturation level of a color.",
                                "Tactile Texture" to "The actual physical surface feel of textile materials (e.g., velvet vs silk)."
                            ),
                            visualExplanation = "Interactive representation of vertical, horizontal, diagonal, and curved lines showing silhouette elongation and slimming effects in apparel.",
                            industrialRelevance = "Used daily by textile designers to conceptualize print repeats, jacquard weaves, and garment collections.",
                            quickRevisionSummary = "Design elements are tools: Line, Shape, Form, Space, Texture, and Color.",
                            practicalApplication = "Creating texture boards and moodboards using mixed textile swatches.",
                            quizQuestions = listOf(
                                QuizQuestion("q1", "Which element of design creates an optical illusion of height when used vertically in a garment?", listOf("Horizontal Line", "Vertical Line", "Diagonal Line", "Curved Line"), 1, "Vertical lines draw the eye up and down, visually slimming and lengthening."),
                                QuizQuestion("q2", "Adding white to a pure color creates a:", listOf("Shade", "Tone", "Tint", "Secondary Color"), 2, "A tint is produced by mixing a pure hue with white.")
                            )
                        ),
                        TopicContent(
                            id = "bvtd111_u1_t2",
                            title = "Principles of Design: Balance, Proportion, Rhythm & Harmony",
                            unitNumber = 1,
                            overview = "Guidelines for arranging design elements to create aesthetically pleasing and unified textile and apparel compositions.",
                            keyPoints = listOf(
                                "Balance: Symmetrical (formal) and Asymmetrical (informal/dynamic).",
                                "Proportion: The Golden Ratio and relation of garment parts to whole body.",
                                "Rhythm: Repetition, radiation, progression, and continuous line movement.",
                                "Emphasis / Focal Point: Center of interest in a garment or textile layout.",
                                "Harmony / Unity: Cohesive interplay of all design components."
                            ),
                            importantTerms = mapOf(
                                "Symmetrical Balance" to "Mirror-image distribution of visual weight across the center line.",
                                "Asymmetrical Balance" to "Unequal visual weights balanced dynamically through color, texture, or placement.",
                                "Golden Ratio" to "Mathematical proportion (approx 1:1.618) universally perceived as harmonious."
                            ),
                            visualExplanation = "Diagrams showing symmetrical vs asymmetrical garment bodices and print motif distributions.",
                            industrialRelevance = "Ensures aesthetic balance in high-fashion collections and commercial apparel lines.",
                            quickRevisionSummary = "Principles organize elements: Balance, Proportion, Emphasis, Rhythm, Harmony.",
                            practicalApplication = "Evaluating garment aesthetics against standard grading and proportion charts.",
                            quizQuestions = listOf(
                                QuizQuestion("q3", "Formal balance is also known as:", listOf("Asymmetrical Balance", "Radial Balance", "Symmetrical Balance", "Discordant Balance"), 2, "Symmetrical balance is equal on both sides, creating formal poise.")
                            )
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Classification of Textile Fibres",
                    description = "Comprehensive study of natural and synthetic fibres, their chemical origin, and end-use properties.",
                    topics = listOf(
                        TopicContent(
                            id = "bvtd111_u2_t1",
                            title = "Natural Fibres: Cellulosic & Protein",
                            unitNumber = 2,
                            overview = "Study of cotton, linen, jute (plant/cellulosic origin) and wool, silk (animal/protein origin).",
                            keyPoints = listOf(
                                "Cotton: King of fibres, high absorbency, breathable, loses strength in acids.",
                                "Linen: Extracted from flax bast, high tensile strength, natural luster, prone to creasing.",
                                "Wool: Natural crimp, high warmth, felting property, protein keratin.",
                                "Silk: Queen of fibres, continuous filament secreted by Bombyx mori silkworm, triangular prism cross-section gives sheen."
                            ),
                            importantTerms = mapOf(
                                "Filament" to "Continuous long fibre strand measured in meters/kilometers (e.g., Silk).",
                                "Staple Fibre" to "Short discrete fibres measured in inches or centimeters (e.g., Cotton, Wool).",
                                "Sericin" to "Natural gummy substance coating raw silk fibroin."
                            ),
                            visualExplanation = "Cross-sectional diagrams of kidney-bean shaped cotton, triangular silk, and scaled wool under microscope.",
                            industrialRelevance = "Foundation for fabric sourcing, dye selection, and spinning mill procurement.",
                            quickRevisionSummary = "Plant fibres = Cellulose (Cotton/Linen); Animal fibres = Protein (Wool/Silk).",
                            practicalApplication = "Microscopic identification and burning test verification.",
                            quizQuestions = listOf(
                                QuizQuestion("q4", "Which natural fibre is known as the Queen of Textiles?", listOf("Cotton", "Wool", "Silk", "Linen"), 2, "Silk is universally known as the Queen of Textiles due to its luxurious sheen and hand.")
                            )
                        ),
                        TopicContent(
                            id = "bvtd111_u2_t2",
                            title = "Synthetic & Regenerated Fibres: Polyester, Nylon & Viscose",
                            unitNumber = 2,
                            overview = "Study of man-made fibres synthesized from petrochemicals or regenerated from wood pulp.",
                            keyPoints = listOf(
                                "Viscose Rayon: Regenerated cellulose fibre, high drape, soft hand, biodegradable.",
                                "Polyester (PET): Most widely produced synthetic, wrinkle resistant, thermoplastic, blends well with cotton.",
                                "Nylon (Polyamide): First true synthetic fibre, exceptional abrasion resistance and elasticity.",
                                "Acrylic: Synthetic wool substitute, warm and lightweight."
                            ),
                            importantTerms = mapOf(
                                "Thermoplastic" to "Property of synthetic fibres to soften with heat and set into permanent shapes/creases.",
                                "Melt Spinning" to "Extrusion method used for polyester and nylon through spinneret holes."
                            ),
                            visualExplanation = "Extrusion process flowchart showing polymer chips melting, spinneret extrusion, and drawing.",
                            industrialRelevance = "Polyester-cotton (PC) and poly-viscose (PV) dominate commercial textile mills in Punjab and global supply chains.",
                            quickRevisionSummary = "Synthetic = Polyester, Nylon, Acrylic; Regenerated = Viscose Rayon, Modal, Lyocell.",
                            practicalApplication = "Chemical solubility tests in hydrochloric acid and acetone.",
                            quizQuestions = listOf(
                                QuizQuestion("q5", "Which fibre was the first fully synthetic commercial fibre?", listOf("Rayon", "Nylon", "Spandex", "Polypropylene"), 1, "Nylon (synthesized in 1935 by Wallace Carothers) was the first 100% synthetic fibre.")
                            )
                        )
                    )
                )
            ),
            practicals = sem1PracticalsBvtd112
        ),
        Subject(
            code = "BVTD 112",
            name = "Design Foundation & Basics of Textile (Practical)",
            semesterNumber = 1,
            category = CourseCategory.MAJOR,
            type = SubjectType.PRACTICAL,
            hoursPerWeek = 4,
            lectureCredits = 0,
            tutorialCredits = 0,
            practicalCredits = 2,
            totalCredits = 2,
            theoryMarks = null,
            practicalMarks = 37,
            internalAssessmentMarks = 13,
            totalMarks = 50,
            syllabusPageRef = "Page 11",
            overview = "Laboratory hands-on practicals for color mixing, texture creation, motif development, and physical testing of textile fibres.",
            learningObjectives = listOf(
                "Develop practical proficiency in color wheels, tint/tone/shade scales.",
                "Render textures for cotton, silk, denim, and wool on croquis sheets.",
                "Execute fibre identification via burning and chemical tests."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Color Theory & Drawing Studio",
                    description = "12-Hue Prang Color Wheel, value scales, and optical texture rendering on sheets.",
                    topics = listOf(
                        TopicContent(
                            id = "bvtd112_t1",
                            title = "Prang Color Wheel & Value Chart Studio",
                            unitNumber = 1,
                            overview = "Laboratory preparation of precise gouache/poster color wheels and monochromatic value scales.",
                            keyPoints = listOf(
                                "Prepare 12-segment color wheel with accurate primary pigment ratios.",
                                "Generate 9-step gray scale and complementary color harmonies.",
                                "Produce monochromatic, analogous, and triadic color schemes."
                            ),
                            importantTerms = mapOf("Color Harmony" to "An aesthetically pleasing combination of colors on the color circle."),
                            visualExplanation = "Step-by-step color mixing guide showing ratios for exact hues.",
                            industrialRelevance = "Essential skill for print designers and shade matching in dye houses.",
                            quickRevisionSummary = "Mastery of Prang wheel and chromatic value scales.",
                            practicalApplication = "Studio portfolio creation for Khalsa College practical viva."
                        )
                    )
                )
            ),
            practicals = sem1PracticalsBvtd112
        ),
        Subject(
            code = "BVTD 113",
            name = "Sewing Techniques (Practical)",
            semesterNumber = 1,
            category = CourseCategory.MAJOR,
            type = SubjectType.PRACTICAL,
            hoursPerWeek = 8,
            lectureCredits = 0,
            tutorialCredits = 0,
            practicalCredits = 4,
            totalCredits = 4,
            theoryMarks = null,
            practicalMarks = 75,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 12",
            overview = "Hands-on garment laboratory course focusing on sewing machine mechanics, temporary & permanent hand stitches, and standard seam engineering.",
            learningObjectives = listOf(
                "Operate and maintain industrial sewing machines safely.",
                "Execute clean hand stitches for couture finishing.",
                "Construct industrial seams, fullness controls (pleats, darts, gathers), and plackets."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Sewing Machine Operation & Stitches",
                    description = "Machine components, threading, tension regulation, basic hand stitches and machine stitches.",
                    topics = listOf(
                        TopicContent(
                            id = "bvtd113_t1",
                            title = "Sewing Machine Parts, Threading & Troubleshooting",
                            unitNumber = 1,
                            overview = "Comprehensive study of single needle lockstitch machine parts: spool pin, thread take-up lever, tension disc, needle bar, feed dog, bobbin case, and pressure foot.",
                            keyPoints = listOf(
                                "Balanced stitch requires equal upper and lower thread tensions meeting in fabric center.",
                                "Skipped stitches usually caused by blunt needle, wrong needle size, or incorrect timing.",
                                "Thread breakage caused by overtension, burr on hook, or low quality thread."
                            ),
                            importantTerms = mapOf(
                                "Lockstitch" to "Stitch formed by interlocking two threads (needle thread and bobbin thread).",
                                "Feed Dog" to "Toothed metal mechanism beneath the needle plate that moves fabric forward."
                            ),
                            visualExplanation = "Machine anatomy diagram showing thread path through guides, tension discs, and needle eye.",
                            industrialRelevance = "Garment manufacturing operator training and quality control inspection.",
                            quickRevisionSummary = "Proper threading and tension balance are the keys to flawless stitching.",
                            practicalApplication = "Daily machine cleaning, oiling, and needle replacement routines."
                        )
                    )
                )
            ),
            practicals = sem1PracticalsBvtd113
        ),
        Subject(
            code = "BVTD 114",
            name = "Introduction to Enterpenureship",
            semesterNumber = 1,
            category = CourseCategory.MINOR,
            type = SubjectType.THEORY,
            hoursPerWeek = 4,
            lectureCredits = 4,
            tutorialCredits = 0,
            practicalCredits = 0,
            totalCredits = 4,
            theoryMarks = 75,
            practicalMarks = null,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 13-14",
            overview = "Entrepreneurship principles tailored for boutique owners, textile design studios, and apparel manufacturing ventures.",
            learningObjectives = listOf(
                "Understand the entrepreneurial mindset and business innovation.",
                "Formulate feasibility studies for textile startups.",
                "Navigate government funding schemes (MSME, Mudra, Startup India)."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Entrepreneurial Dynamics & Idea Generation",
                    description = "Concept of entrepreneurship, entrepreneurial traits, identifying market gaps in apparel industry.",
                    topics = listOf(
                        TopicContent(
                            id = "bvtd114_t1",
                            title = "Entrepreneurship in Textile & Fashion Sectors",
                            unitNumber = 1,
                            overview = "How to launch a boutique, craft brand, or garment export unit in India.",
                            keyPoints = listOf(
                                "Identifying niche markets (e.g., sustainable organic cotton clothing, Phulkari revival).",
                                "SWOT analysis (Strengths, Weaknesses, Opportunities, Threats) for fashion ventures.",
                                "Understanding fixed capital vs working capital in textile production."
                            ),
                            importantTerms = mapOf(
                                "Working Capital" to "Funds needed for day-to-day operations like buying fabric and paying wages.",
                                "MSME" to "Micro, Small & Medium Enterprises classification in India."
                            ),
                            visualExplanation = "Startup flowchart from ideation, feasibility, sample production, to market launch.",
                            industrialRelevance = "Prepares vocational graduates to build independent design brands in Punjab and beyond.",
                            quickRevisionSummary = "Entrepreneurship = Innovation + Risk Management + Resource Execution."
                        )
                    )
                )
            )
        ),
        Subject(
            code = "CS-BVTD111",
            name = "Computer Application-I",
            semesterNumber = 1,
            category = CourseCategory.MINOR,
            type = SubjectType.THEORY_AND_PRACTICAL,
            hoursPerWeek = 6,
            lectureCredits = 2,
            tutorialCredits = 0,
            practicalCredits = 2,
            totalCredits = 4,
            theoryMarks = 50,
            practicalMarks = 25,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 15-16",
            overview = "Introduction to digital systems, word processing for specification sheets, spreadsheets for garment costing, and graphic tools.",
            learningObjectives = listOf(
                "Master office productivity tools for tech packs and cost sheets.",
                "Understand computer hardware and operating system fundamentals.",
                "Begin vector and raster motif digital rendering."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Computer Fundamentals & Office Automation",
                    description = "Operating systems, spreadsheets for material BOM (Bill of Materials) and costing calculations.",
                    topics = listOf(
                        TopicContent(
                            id = "cs111_t1",
                            title = "Spreadsheets for Apparel Costing & BOM",
                            unitNumber = 1,
                            overview = "Using formulas and tables to calculate fabric consumption, trim costs, CMT (Cut-Make-Trim), and profit margins.",
                            keyPoints = listOf(
                                "Calculating fabric consumption per dozen garments.",
                                "Summing thread, buttons, zippers, and packaging costs.",
                                "Automating overhead and taxation formulas."
                            ),
                            importantTerms = mapOf("BOM" to "Bill of Materials listing every item needed to build a garment."),
                            visualExplanation = "Spreadsheet layout showing Fabric + Trims + Labor = Cost Price.",
                            industrialRelevance = "Standard skill for fashion merchandisers and production managers."
                        )
                    )
                )
            )
        ),
        Subject(
            code = "BCSV-1129",
            name = "Communication Skills in English-I",
            semesterNumber = 1,
            category = CourseCategory.ABILITY_ENHANCEMENT,
            type = SubjectType.THEORY_AND_PRACTICAL,
            hoursPerWeek = 3,
            lectureCredits = 3,
            tutorialCredits = 0,
            practicalCredits = 1,
            totalCredits = 4,
            theoryMarks = 60,
            practicalMarks = 15,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 17-19",
            overview = "Business English communication, vocabulary development for apparel trade, client correspondence, and presentations.",
            learningObjectives = listOf(
                "Communicate professionally with clients and suppliers.",
                "Draft formal inquiry emails, quotation requests, and tech pack notes.",
                "Deliver clear oral presentations on design concepts."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Business Communication & Apparel Terminology",
                    description = "Writing skills, reading comprehension, and professional vocabulary.",
                    topics = listOf(
                        TopicContent(
                            id = "bcsv1129_t1",
                            title = "Professional Correspondence in Fashion Trade",
                            unitNumber = 1,
                            overview = "Writing formal purchase orders, delivery memos, and design proposal emails.",
                            keyPoints = listOf("Clear subject lines, courteous tone, exact specifications and deadline terms."),
                            importantTerms = mapOf("Purchase Order" to "Legally binding commercial document authorizing production."),
                            visualExplanation = "Template of an international apparel export inquiry email.",
                            industrialRelevance = "Crucial for dealing with international buyers and domestic retailers."
                        )
                    )
                )
            )
        ),
        Subject(
            code = "BHPB-1101",
            name = "Punjabi (Compulsory) / Basic Punjabi / Punjab History & Culture",
            semesterNumber = 1,
            category = CourseCategory.ABILITY_ENHANCEMENT,
            type = SubjectType.THEORY,
            hoursPerWeek = 4,
            lectureCredits = 4,
            tutorialCredits = 0,
            practicalCredits = 0,
            totalCredits = 4,
            theoryMarks = 75,
            practicalMarks = null,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 20-24",
            overview = "Regional language and cultural studies including traditional crafts, Punjabi textile heritage like Phulkari, Bagh, and Durrie weaving traditions.",
            learningObjectives = listOf(
                "Gain literary and linguistic proficiency in Punjabi.",
                "Understand the cultural legacy of Punjab’s handicraft and embroidery traditions."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Punjabi Literature & Textile Heritage",
                    description = "Selected literary texts and historical survey of Punjabi handicraft traditions.",
                    topics = listOf(
                        TopicContent(
                            id = "bhpb1101_t1",
                            title = "Traditional Punjabi Phulkari & Folk Crafts",
                            unitNumber = 1,
                            overview = "Study of Phulkari embroidery (Chope, Subhar, Bagh) worked on coarse khaddar fabric using untwisted silk floss (pat).",
                            keyPoints = listOf(
                                "Geometrical darn stitch worked from the reverse side.",
                                "Symbolic motifs: Marigold, peacock, geometric gardens.",
                                "GI (Geographical Indication) status of Punjab Phulkari."
                            ),
                            importantTerms = mapOf("Pat" to "Untwisted, glossy silk embroidery thread used in Phulkari."),
                            visualExplanation = "Pattern diagram showing darn stitch reverse-side counting technique.",
                            industrialRelevance = "Invaluable for ethnic apparel collections and contemporary craft revival projects in Punjab."
                        )
                    )
                )
            )
        ),
        Subject(
            code = "ZDA111",
            name = "Drug Abuse: Problems, Management and Prevention (Compulsory)",
            semesterNumber = 1,
            category = CourseCategory.VALUE_ADDED,
            type = SubjectType.THEORY,
            hoursPerWeek = 1,
            lectureCredits = 1,
            tutorialCredits = 0,
            practicalCredits = 0,
            totalCredits = 1,
            theoryMarks = null,
            practicalMarks = null,
            internalAssessmentMarks = 25,
            totalMarks = 25,
            syllabusPageRef = "Page 25-27",
            overview = "Value-added awareness curriculum on public health, substance abuse prevention, psychological management, and community awareness.",
            learningObjectives = listOf(
                "Understand medical and social impacts of substance abuse.",
                "Identify preventive measures and community support mechanisms."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Substance Abuse: Nature, Impact & Prevention",
                    description = "Biological mechanisms, social consequences, and rehabilitation approaches.",
                    topics = listOf(
                        TopicContent(
                            id = "zda111_t1",
                            title = "Awareness & Preventive Education",
                            unitNumber = 1,
                            overview = "Promoting healthy lifestyle choices, youth counseling, and societal support networks.",
                            keyPoints = listOf("Understanding physical and psychological dependency factors.", "Role of education and de-addiction centers."),
                            importantTerms = mapOf("Rehabilitation" to "Structured process of physical and mental recovery."),
                            visualExplanation = "Infographic on health, wellness, and community resilience.",
                            industrialRelevance = "Fostering disciplined, healthy workplace environments in manufacturing industries."
                        )
                    )
                )
            )
        )
    )

    private val semester2Subjects = listOf(
        Subject(
            code = "BVTD 121",
            name = "Introduction to Fashion",
            semesterNumber = 2,
            category = CourseCategory.MAJOR,
            type = SubjectType.THEORY,
            hoursPerWeek = 4,
            lectureCredits = 4,
            tutorialCredits = 0,
            practicalCredits = 0,
            totalCredits = 4,
            theoryMarks = 75,
            practicalMarks = null,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 28-29",
            overview = "Comprehensive introduction to the fashion world, fashion cycle stages, adoption theories, fashion capitals, and key designers.",
            learningObjectives = listOf(
                "Define essential fashion vocabulary (Fad, Trend, Classic, Silhouette).",
                "Analyze the stages of the 5-phase Fashion Cycle.",
                "Evaluate trickle-down, trickle-up, and trickle-across diffusion theories.",
                "Study iconic Indian and international designers."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Fashion Terminology & Fashion Cycle",
                    description = "Terminology, life cycle of styles, consumer adoption categories.",
                    topics = listOf(
                        TopicContent(
                            id = "bvtd121_u1_t1",
                            title = "Fashion Terminology & The 5-Stage Fashion Cycle",
                            unitNumber = 1,
                            overview = "Fashion is the prevailing style accepted by a majority at a given time. The cycle tracks introduction, rise, peak, decline, and obsolescence.",
                            keyPoints = listOf(
                                "Introduction Stage: New designs shown at runway/haute couture, high prices, limited volume.",
                                "Rise Stage: Mass manufacturers adapt style, featured in media, increasing sales.",
                                "Peak / Culmination: Style is at height of popularity, mass-market retail availability.",
                                "Decline Stage: Market saturation, discounting and bargain racks.",
                                "Obsolescence: Dead style; consumers moved on to next cycle."
                            ),
                            importantTerms = mapOf(
                                "Fad" to "A short-lived fashion craze that enters quickly, peaks rapidly, and vanishes.",
                                "Classic" to "A timeless style that remains in fashion over decades (e.g., Trench Coat, Little Black Dress, Blue Jeans).",
                                "Haute Couture" to "Custom-fitted, high-end high fashion hand-crafted in Paris according to legal standards."
                            ),
                            visualExplanation = "Bell curve chart depicting the 5 stages of the Fashion Life Cycle with price vs volume trajectories.",
                            industrialRelevance = "Crucial for retail buying, inventory clearance timing, and collection launching schedules.",
                            quickRevisionSummary = "Cycle = Introduction -> Rise -> Peak -> Decline -> Obsolescence.",
                            quizQuestions = listOf(
                                QuizQuestion("f1", "A style that stays in fashion for an exceptionally long period without major changes is called a:", listOf("Fad", "Trend", "Classic", "Avant-garde"), 2, "A classic (like a trench coat or white button-down) persists over generations.")
                            )
                        ),
                        TopicContent(
                            id = "bvtd121_u1_t2",
                            title = "Fashion Adoption Theories: Trickle-Down, Up & Across",
                            unitNumber = 1,
                            overview = "How fashion movements spread through socioeconomic classes.",
                            keyPoints = listOf(
                                "Trickle-Down (Downward Flow): Starts with luxury elite/royalty, copied by mass market (e.g., Royal wedding gowns).",
                                "Trickle-Up (Upward Flow / Bubble-up): Starts with youth culture/streetwear, adopted by luxury houses (e.g., Ripped jeans, hoodies, punk safety pins).",
                                "Trickle-Across (Horizontal Flow): Mass dissemination across all price points simultaneously via fast fashion and social media."
                            ),
                            importantTerms = mapOf(
                                "Trickle-Up Theory" to "Innovation originating from subcultures and street styles moving to high fashion.",
                                "Fast Fashion" to "Rapid translation of catwalk trends into low-cost retail stock."
                            ),
                            visualExplanation = "Flow diagram illustrating directional vectors of Downward, Upward, and Horizontal fashion flows.",
                            industrialRelevance = "Helps fashion forecasters and trend spotters anticipate upcoming street and runway shifts.",
                            quickRevisionSummary = "Down (Elite -> Mass), Up (Street -> Catwalk), Across (Simultaneous peer-to-peer)."
                        )
                    )
                )
            )
        ),
        Subject(
            code = "BVTD 122",
            name = "Garment Sewing (Practical)",
            semesterNumber = 2,
            category = CourseCategory.MAJOR,
            type = SubjectType.PRACTICAL,
            hoursPerWeek = 8,
            lectureCredits = 0,
            tutorialCredits = 0,
            practicalCredits = 4,
            totalCredits = 4,
            theoryMarks = null,
            practicalMarks = 75,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 30",
            overview = "Drafting, pattern making, cutting, and stitching of full garments including basic bodice blocks, sleeves, collars, and kids/adult wear.",
            learningObjectives = listOf(
                "Draft standard anthropometric body measurements.",
                "Construct sleeve variations: Plain, Puff, Bell, Raglan.",
                "Construct collar variations: Mandarin/Chinese, Peter Pan, Shirt Collar.",
                "Stitch complete test garments with professional finishing."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Pattern Drafting & Garment Assembly",
                    description = "Bodice blocks, sleeve variations, collar constructions, and complete garment assembly.",
                    topics = listOf(
                        TopicContent(
                            id = "bvtd122_t1",
                            title = "Basic Bodice Block & Dart Manipulation",
                            unitNumber = 1,
                            overview = "Constructing a 2D foundation pattern using body measurements, and rotating darts using pivoting or slash-and-spread methods.",
                            keyPoints = listOf(
                                "Standard measurements needed: Bust circumference, waist circumference, across back, nape to waist.",
                                "Dart manipulation transfers bust fullness to shoulder, side seam (French dart), or waist.",
                                "Grainline alignment on warp yarns prevents garment twisting."
                            ),
                            importantTerms = mapOf(
                                "Apex" to "Highest point of the bust mound towards which darts point.",
                                "Slash and Spread" to "Pattern-making method to add fullness or relocate dart intake."
                            ),
                            visualExplanation = "Drafting schematic of front and back bodice blocks showing ease allowances and dart legs.",
                            industrialRelevance = "Core fundamental for pattern cutters and technical sample makers in export houses.",
                            quickRevisionSummary = "Accurate measurements + dart balance = perfect garment fit."
                        )
                    )
                )
            )
        ),
        Subject(
            code = "BVTD 123",
            name = "Design Foundation and Basics of Textiles - II (Practical)",
            semesterNumber = 2,
            category = CourseCategory.MAJOR,
            type = SubjectType.PRACTICAL,
            hoursPerWeek = 8,
            lectureCredits = 0,
            tutorialCredits = 0,
            practicalCredits = 4,
            totalCredits = 4,
            theoryMarks = null,
            practicalMarks = 75,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 31",
            overview = "Advanced weave graph representations (Plain, Twill, Satin/Sateen), surface design repeats, block printing, and tie-dye resist methods.",
            learningObjectives = listOf(
                "Plot weave structures on point paper / design graph sheets.",
                "Develop repeat units (Half-drop, Brick, All-over) for fabric print design.",
                "Execute traditional resist dyeing: Shibori, Bandhani, and Batik."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Weave Structure & Surface Ornamentation Studio",
                    description = "Woven structure plotting on graph paper and textile surface print repeat patterns.",
                    topics = listOf(
                        TopicContent(
                            id = "bvtd123_t1",
                            title = "Basic Weaves: Plain, Twill & Satin Graph Plotting",
                            unitNumber = 1,
                            overview = "Plotting warp and weft intersections on point paper. Plain (1/1), Twill (2/1, 2/2 diagonal wale lines), and Satin (floating yarns for smooth luster).",
                            keyPoints = listOf(
                                "Plain weave has the highest number of interlacing points, highest durability.",
                                "Twill weave features characteristic diagonal ribs (wales) with 45-degree angle.",
                                "Satin has long floats producing high luster but lower abrasion resistance."
                            ),
                            importantTerms = mapOf(
                                "Warp" to "Lengthwise yarns held under tension on the loom.",
                                "Weft" to "Crosswise yarns inserted through the shed.",
                                "Float" to "Length of yarn passing over two or more perpendicular yarns without interlacing."
                            ),
                            visualExplanation = "Interactive weave grid showing 1/1 checkerboard Plain Weave and 2/2 diagonal step Twill Weave.",
                            industrialRelevance = "Direct application in dobby and jacquard loom programming and fabric quality inspection."
                        )
                    )
                )
            )
        ),
        Subject(
            code = "BVTD 124",
            name = "Enterprise Planning",
            semesterNumber = 2,
            category = CourseCategory.MINOR,
            type = SubjectType.THEORY,
            hoursPerWeek = 4,
            lectureCredits = 4,
            tutorialCredits = 0,
            practicalCredits = 0,
            totalCredits = 4,
            theoryMarks = 75,
            practicalMarks = null,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 32-33",
            overview = "Business planning, plant layout, raw material procurement, quality assurance systems, and marketing for apparel micro-enterprises.",
            learningObjectives = listOf(
                "Prepare comprehensive business plan for a boutique or printing unit.",
                "Design effective production floor layouts.",
                "Implement total quality management (TQM) in garment manufacturing."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Business Model & Production Planning",
                    description = "Factory layouts, plant capacity estimation, supplier management, and break-even analysis.",
                    topics = listOf(
                        TopicContent(
                            id = "bvtd124_t1",
                            title = "Apparel Production Planning & Quality Assurance",
                            unitNumber = 1,
                            overview = "Balancing production lines, progressive bundle systems, and ISO/AQL quality inspection standards.",
                            keyPoints = listOf("Line balancing reduces idle time.", "AQL (Acceptable Quality Limit) 2.5/4.0 standard inspection."),
                            importantTerms = mapOf("AQL" to "Statistical sampling standard used for final shipment release."),
                            visualExplanation = "Factory workflow layout from Fabric Warehouse -> Cutting -> Sewing -> Finishing -> Packing.",
                            industrialRelevance = "Standard protocol for production managers in garment export factories."
                        )
                    )
                )
            )
        ),
        Subject(
            code = "CS-BVTD121",
            name = "Computer Applications-II (Practical)",
            semesterNumber = 2,
            category = CourseCategory.MINOR,
            type = SubjectType.PRACTICAL,
            hoursPerWeek = 8,
            lectureCredits = 0,
            tutorialCredits = 0,
            practicalCredits = 4,
            totalCredits = 4,
            theoryMarks = null,
            practicalMarks = 75,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 34-35",
            overview = "Computer Aided Design (CAD) software for textile prints, digital moodboards, color separations, and fashion illustrations.",
            learningObjectives = listOf(
                "Design seamless digital print repeats using vector software.",
                "Digitize fashion croquis and drape garment textures.",
                "Generate industrial tech packs and digital lookbooks."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Textile CAD & Digital Illustration Studio",
                    description = "Digital repeat creation, colorway palettes, and 3D digital drape simulation.",
                    topics = listOf(
                        TopicContent(
                            id = "cs121_t1",
                            title = "Digital Seamless Pattern Repeats & Colorways",
                            unitNumber = 1,
                            overview = "Setting up seamless pattern offsets, raster to vector conversion, and generating 4-season colorways.",
                            keyPoints = listOf("Offset filters ensure boundary-less horizontal and vertical repeats for rotary screen printing."),
                            importantTerms = mapOf("Colorway" to "The same print design rendered in alternative curated color combinations."),
                            visualExplanation = "Screen layout showing bounding box tile repetition.",
                            industrialRelevance = "Standard daily work of digital textile designers and print mills."
                        )
                    )
                )
            )
        ),
        Subject(
            code = "BCSV-1229",
            name = "Communication Skills in English-II",
            semesterNumber = 2,
            category = CourseCategory.ABILITY_ENHANCEMENT,
            type = SubjectType.THEORY_AND_PRACTICAL,
            hoursPerWeek = 3,
            lectureCredits = 3,
            tutorialCredits = 0,
            practicalCredits = 1,
            totalCredits = 4,
            theoryMarks = 60,
            practicalMarks = 15,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 36-38",
            overview = "Advanced technical communication, resume crafting, client contract negotiation, and verbal proficiency for fashion careers.",
            learningObjectives = listOf(
                "Write professional creative resumes and portfolio cover letters.",
                "Conduct mock design presentations and interview rounds."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Advanced Career Communication",
                    description = "Job interviews, portfolio pitches, and client communication.",
                    topics = listOf(
                        TopicContent(
                            id = "bcsv1229_t1",
                            title = "Portfolio Presentation & Pitching Skills",
                            unitNumber = 1,
                            overview = "Presenting visual moodboards and design rationale persuasively to buyers and juries.",
                            keyPoints = listOf("Articulate design inspiration, fabric choices, target market, and costing clearly."),
                            importantTerms = mapOf("Design Pitch" to "Structured presentation explaining collection concept and market viability.")
                        )
                    )
                )
            )
        ),
        Subject(
            code = "BHPB-1201",
            name = "Punjabi (Compulsory) / Basic Punjabi / Punjab History & Culture-II",
            semesterNumber = 2,
            category = CourseCategory.ABILITY_ENHANCEMENT,
            type = SubjectType.THEORY,
            hoursPerWeek = 4,
            lectureCredits = 4,
            tutorialCredits = 0,
            practicalCredits = 0,
            totalCredits = 4,
            theoryMarks = 75,
            practicalMarks = null,
            internalAssessmentMarks = 25,
            totalMarks = 100,
            syllabusPageRef = "Page 39-43",
            overview = "Punjabi literature, cultural heritage, and regional handicrafts of Punjab.",
            learningObjectives = listOf("Deepen appreciation of Punjab's linguistic and cultural legacy."),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Punjabi Culture & Folk Traditions",
                    description = "Folk songs, festivals, and cultural continuity.",
                    topics = listOf(
                        TopicContent(
                            id = "bhpb1201_t1",
                            title = "Folk Traditions and Traditional Craftsmanship of Punjab",
                            unitNumber = 1,
                            overview = "Appreciation of Punjabi folk motifs, traditional looms (Khaddi), and vernacular embroidery styles."
                        )
                    )
                )
            )
        ),
        Subject(
            code = "ZDA121",
            name = "Drug Abuse: Problems, Management and Prevention (Compulsory)",
            semesterNumber = 2,
            category = CourseCategory.VALUE_ADDED,
            type = SubjectType.THEORY,
            hoursPerWeek = 1,
            lectureCredits = 1,
            tutorialCredits = 0,
            practicalCredits = 0,
            totalCredits = 1,
            theoryMarks = null,
            practicalMarks = null,
            internalAssessmentMarks = 25,
            totalMarks = 25,
            syllabusPageRef = "Page 44-45",
            overview = "Community prevention, societal rehabilitation, and mental health counseling frameworks.",
            learningObjectives = listOf("Foster supportive peer networks and preventive mental health awareness."),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Community Rehabilitation & Wellness",
                    description = "Social support systems and preventive counseling.",
                    topics = listOf(
                        TopicContent(
                            id = "zda121_t1",
                            title = "Community Support & Healthy Living",
                            unitNumber = 1,
                            overview = "Youth empowerment, stress management techniques, and positive campus community initiatives."
                        )
                    )
                )
            )
        )
    )

    val semesters = listOf(
        Semester(
            number = 1,
            title = "Semester I",
            yearNumber = 1,
            totalCredits = 25,
            totalMarks = 600,
            totalHoursPerWeek = 30,
            subjects = semester1Subjects
        ),
        Semester(
            number = 2,
            title = "Semester II",
            yearNumber = 1,
            totalCredits = 29,
            totalMarks = 700,
            totalHoursPerWeek = 40,
            subjects = semester2Subjects
        )
    )

    val academicYears = listOf(
        AcademicYear(
            yearNumber = 1,
            title = "1st Year (Certificate / Diploma Foundation)",
            semesters = semesters,
            isSyllabusAvailable = true
        ),
        AcademicYear(
            yearNumber = 2,
            title = "2nd Year (Advanced Diploma)",
            semesters = listOf(
                Semester(3, "Semester III", 2, 30, 700, 35, emptyList()),
                Semester(4, "Semester IV", 2, 30, 700, 35, emptyList())
            ),
            isSyllabusAvailable = false
        ),
        AcademicYear(
            yearNumber = 3,
            title = "3rd Year (B.Voc Degree & Industry Internship)",
            semesters = listOf(
                Semester(5, "Semester V", 3, 30, 700, 35, emptyList()),
                Semester(6, "Semester VI", 3, 30, 700, 35, emptyList())
            ),
            isSyllabusAvailable = false
        )
    )

    val sampleResources = listOf(
        CourseResource(
            id = "res_1",
            title = "Textile Fibres Identification Lab Manual",
            subjectCode = "BVTD 112",
            category = "Manual",
            description = "Complete burning test chart, solubility tables, and microscopic reference sketches.",
            downloadSize = "2.4 MB",
            format = "PDF"
        ),
        CourseResource(
            id = "res_2",
            title = "Industrial Sewing Stitches & Seams Handbook",
            subjectCode = "BVTD 113",
            category = "Notes",
            description = "Step-by-step illustrations of plain, French, flat-felled seams with seam allowances.",
            downloadSize = "4.1 MB",
            format = "PDF"
        ),
        CourseResource(
            id = "res_3",
            title = "Prang Color Harmony & Motif Palette Guide",
            subjectCode = "BVTD 111",
            category = "Diagram",
            description = "High-res color wheels, complementary pairs, and seasonal color forecasting swatches.",
            downloadSize = "1.8 MB",
            format = "PDF"
        ),
        CourseResource(
            id = "res_4",
            title = "Introduction to Fashion - Model Exam Paper with Solutions",
            subjectCode = "BVTD 121",
            category = "Question Paper",
            description = "Past mid-term and end-term questions on fashion cycle, terminology, and designers.",
            downloadSize = "1.2 MB",
            format = "PDF"
        ),
        CourseResource(
            id = "res_5",
            title = "Point Paper Weave Plotting Exercises",
            subjectCode = "BVTD 123",
            category = "Manual",
            description = "Grid templates for 1/1 plain, 2/2 twill, and 5-end satin weaves.",
            downloadSize = "3.0 MB",
            format = "PDF"
        )
    )

    val careerRoles = listOf(
        CareerRole(
            title = "Textile Print & Surface Designer",
            sector = "Textile Mills & Design Studios",
            description = "Create original print repeats, jacquard weave patterns, embroideries, and colorways for home textiles and fashion brands.",
            keySkills = listOf("Color Harmony", "Weave Structures", "Motif Development", "Screen Printing Tech"),
            standardTools = listOf("Adobe Photoshop", "Illustrator", "NedGraphics", "Textile CAD"),
            industryScope = "High demand in Amritsar, Ludhiana, Surat, and export fashion houses."
        ),
        CareerRole(
            title = "Apparel Production & Merchandiser",
            sector = "Garment Manufacturing & Exports",
            description = "Bridge the gap between buyers and the factory floor. Manage costing, Bill of Materials (BOM), sample approvals, and shipment schedules.",
            keySkills = listOf("Fabric Sourcing", "Garment Costing", "AQL Quality Inspection", "Supply Chain"),
            standardTools = listOf("Excel ERP", "Tech Pack Software", "FastReact"),
            industryScope = "Core role across all garment manufacturing clusters in North India and international brands."
        ),
        CareerRole(
            title = "Pattern Maker & Technical Designer",
            sector = "Fashion Houses & Boutiques",
            description = "Transform fashion sketches into precise 2D and 3D patterns with perfect fit, dart balance, and grading across sizes.",
            keySkills = listOf("Anthropometrics", "Bodice Drafting", "Dart Manipulation", "Grading Rules"),
            standardTools = listOf("Optitex CAD", "Gerber Accumark", "CLO 3D"),
            industryScope = "Crucial for boutique owners, bespoke fashion ateliers, and industrial export units."
        ),
        CareerRole(
            title = "Textile & Apparel Entrepreneur",
            sector = "Independent Ventures & Startups",
            description = "Launch your own designer boutique, sustainable craft brand, customized uniform production, or digital textile printing service.",
            keySkills = listOf("Business Planning", "Brand Identity", "E-commerce", "Vendor Management"),
            standardTools = listOf("Social Commerce", "Inventory POS", "Canva / Adobe Suite"),
            industryScope = "Supported by Govt. MSME, Mudra loans, and growing demand for heritage crafts."
        )
    )

    fun getAllSubjects(): List<Subject> {
        return semesters.flatMap { it.subjects }
    }

    fun getSubjectByCode(code: String): Subject? {
        return getAllSubjects().find { it.code.equals(code, ignoreCase = true) }
    }

    fun getAllPracticals(): List<PracticalActivity> {
        return getAllSubjects().flatMap { it.practicals }
    }
}
