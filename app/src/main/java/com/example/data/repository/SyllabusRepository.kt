package com.example.data.repository

import com.example.data.model.*

object SyllabusRepository {

    val collegeInfo = mapOf(
        "collegeName" to "Khalsa College, Amritsar",
        "department" to "P.G. Department of Fashion Designing & Textile Technology",
        "courseName" to "B.Voc. (Textile Design & Apparel Technology)",
        "session" to "Session 2026-27 (Official GNDU NEP Scheme)",
        "courseType" to "Vocational Degree (UGC Recognized / GNDU Aligned)",
        "duration" to "3 Years (6 Semesters)",
        "syllabusVersion" to "Complete Semester-I & II Curriculum Scheme"
    )

    // =========================================================================
    // SEMESTER 1 PRACTICALS
    // =========================================================================

    // BVTD 112: Design Foundation & Basics of Textile (practical) - 2 Credits, P: 37, IA: 13, Total: 50, Page 11
    val sem1PracticalsBvtd112 = listOf(
        PracticalActivity(
            id = "bvtd112_p1_color_wheel",
            title = "12-Hue Color Wheel & Prang Color System",
            subjectCode = "BVTD112",
            objective = "To construct a standard 12-hue color wheel depicting primary (Red, Yellow, Blue), secondary (Orange, Green, Violet), and tertiary colors using poster colors.",
            materialsRequired = listOf(
                "Ivory / Cartridge drawing sheets (A3 size)",
                "Poster / Gouache colors (Camlin/Faber-Castell)",
                "Flat and round synthetic brushes (Sizes 2, 4, 6)",
                "Compass, protractor, and ruler set",
                "Color mixing palette & water containers"
            ),
            theory = "The Prang color system organizes hues based on pigment mixtures. The three primary colors cannot be formed by mixing any other colors. Mixing pairs of primaries produces secondaries, while mixing a primary with an adjacent secondary yields tertiary hues.",
            stepByStepProcedure = listOf(
                "Draw a 20 cm diameter circle and divide into 12 equal 30-degree sectors.",
                "Apply pure Primary hues (Red, Yellow, Blue) in equidistant sectors forming an equilateral triangle.",
                "Mix equal parts of two primaries to create Secondary hues (Orange = Red+Yellow, Green = Yellow+Blue, Violet = Blue+Red) and fill respective sectors.",
                "Mix equal parts of primary and adjacent secondary to form Tertiary hues (Red-Orange, Yellow-Orange, Yellow-Green, Blue-Green, Blue-Violet, Red-Violet).",
                "Allow uniform drying and mount swatch with clean border lines."
            ),
            expectedObservations = "Smooth, opaque color swatches with clean transitions and correct hue chromaticity around the circular spectrum.",
            precautions = listOf(
                "Use clean brushes and water between hue mixtures to prevent muddy contamination.",
                "Maintain uniform pigment consistency (neither too runny nor too thick)."
            ),
            vivaQuestions = listOf(
                "What are the three primary pigment colors?" to "Red, Yellow, and Blue.",
                "What color is obtained by mixing Yellow and Blue in equal proportions?" to "Green (Secondary hue).",
                "What is the difference between hue and chroma?" to "Hue is the name of the color family, while chroma is its saturation or purity level."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 11: Color Wheel & Hue Systems)"
        ),
        PracticalActivity(
            id = "bvtd112_p2_value_intensity",
            title = "Value Scale (Tints & Shades) and Intensity Scales",
            subjectCode = "BVTD112",
            objective = "To create a 9-step monochromatic value scale (tint to shade) and an intensity / chroma scale for primary and secondary hues.",
            materialsRequired = listOf(
                "A3 Cartridge sheets",
                "Poster colors (Primary hues + Titanium White + Lamp Black)",
                "Flat brushes and ruler",
                "Palette"
            ),
            theory = "Value refers to the relative lightness or darkness of a hue. Tints are created by adding white to increase value, while shades are created by adding black to decrease value. Intensity represents the brightness or dullness produced by adding the complementary hue or neutral gray.",
            stepByStepProcedure = listOf(
                "Draw a 9-step grid (each step 3cm x 3cm) for the Value Scale.",
                "Step 1 = Pure White; Step 5 = Pure Hue; Step 9 = Pure Black.",
                "Gradually mix incremental amounts of white to pure hue for steps 2, 3, 4 (Tints).",
                "Gradually mix incremental amounts of black to pure hue for steps 6, 7, 8 (Shades).",
                "Draw a 7-step grid for the Intensity scale; gradually mix complementary hue to reduce chroma towards neutral chromatic gray."
            ),
            expectedObservations = "Equidistant visual gradations in lightness and chroma without abrupt color jumps.",
            precautions = listOf(
                "Add black very sparingly as it overpowers color pigments quickly.",
                "Ensure uniform brush stroke direction within each square."
            ),
            vivaQuestions = listOf(
                "What is a tint?" to "A hue lightened by the addition of white.",
                "What is a shade?" to "A hue darkened by the addition of black.",
                "How can you lower the intensity of a color without making it darker?" to "By adding its direct complementary color or neutral gray."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 11: Value & Intensity Scales)"
        ),
        PracticalActivity(
            id = "bvtd112_p3_color_schemes",
            title = "Color Harmonies & Schemes in Textile Compositions",
            subjectCode = "BVTD112",
            objective = "To develop textile print compositions illustrating Monochromatic, Analogous, Complementary, Triadic, and Split-Complementary color schemes.",
            materialsRequired = listOf(
                "A4 Sheets with geometric/floral motif outlines",
                "Poster colors and fine brushes (Size 0, 1, 2)",
                "Color chart reference"
            ),
            theory = "Color harmonies are pleasing combinations of colors determined by their relative positions on the color wheel. They govern aesthetic balance in garment styling and textile print collections.",
            stepByStepProcedure = listOf(
                "Trace standard repeat motifs on 5 separate panels.",
                "Panel 1 (Monochromatic): Paint using one hue with its tints and shades.",
                "Panel 2 (Analogous): Paint using 3 adjacent wheel hues (e.g., Yellow, Yellow-Green, Green).",
                "Panel 3 (Complementary): Paint using opposite wheel pairs (e.g., Blue and Orange).",
                "Panel 4 (Triadic): Paint using 3 equidistant hues (e.g., Red, Yellow, Blue).",
                "Panel 5 (Split-Complementary): Paint using 1 base hue plus the two hues adjacent to its complement."
            ),
            expectedObservations = "Visually harmonious compositions demonstrating distinct psychological and aesthetic moods.",
            precautions = listOf("Maintain dominant, sub-dominant, and accent color proportions (60-30-10 rule)."),
            vivaQuestions = listOf(
                "What is an analogous color scheme?" to "A scheme using 2 to 4 hues located next to each other on the color wheel.",
                "Give an example of a complementary color pair." to "Red & Green, Blue & Orange, or Yellow & Violet."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 11: Color Harmonies)"
        ),
        PracticalActivity(
            id = "bvtd112_p4_fibre_burning",
            title = "Identification of Textile Fibres by Burning Test",
            subjectCode = "BVTD112",
            objective = "To classify textile fibres into cellulosic, protein, and synthetic groups using flame reaction, smoke odor, and ash residue examination.",
            materialsRequired = listOf(
                "Fibre tufts: Cotton, Flax, Wool, Pure Silk, Nylon 6,6, Polyester (PET), Viscose Rayon, Acrylic",
                "Bunsen burner / Spirit lamp",
                "Crucible forceps / Tweezers",
                "Heat-resistant ceramic plate & safety goggles"
            ),
            theory = "Combustion behavior is governed by molecular chemistry. Cellulosic fibres burn quickly with paper smell; protein fibres burn slowly with burning hair smell; synthetic polymers melt into chemical beads.",
            stepByStepProcedure = listOf(
                "Grasp sample with forceps and approach flame edge (observe approaching behavior).",
                "Insert sample into flame (observe burning rate and flame color).",
                "Withdraw from flame (note if self-extinguishing or continuing).",
                "Waft fumes gently towards nose to identify odor.",
                "Examine cool residue with fingers (crushable ash vs hard bead)."
            ),
            expectedObservations = "Cotton: yellow flame, burning paper odor, light grey ash. Wool: sputters, burning hair odor, irregular crushable black ash. Polyester: melts, sweet chemical odor, round hard uncrushable bead.",
            precautions = listOf(
                "Never inhale fumes directly.",
                "Use tweezers to prevent skin burns from molten synthetic polymers."
            ),
            vivaQuestions = listOf(
                "Why does wool self-extinguish when removed from flame?" to "Because of its high moisture content and protein keratin sulfur structure.",
                "How do you distinguish cotton from viscose rayon in burning test?" to "Both burn similarly (cellulosic); chemical solubility or microscopic testing is required for definitive differentiation."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 11: Fibre Identification Testing)"
        ),
        PracticalActivity(
            id = "bvtd112_p5_microscopic",
            title = "Microscopic Examination of Textile Fibres",
            subjectCode = "BVTD112",
            objective = "To prepare temporary slide mounts and observe the longitudinal and cross-sectional morphology of natural and man-made fibres under a compound microscope.",
            materialsRequired = listOf(
                "Compound optical microscope (100x & 400x magnification)",
                "Glass slides, cover slips, dissecting needle, razor blade",
                "Glycerol / Distilled water mountant",
                "Fibre specimens (Cotton, Wool, Silk, Polyester, Nylon)"
            ),
            theory = "Optical microscopy reveals distinguishing structural features such as natural convolutions in cotton, surface scales in wool, triangular rounded cross-sections in silk, and smooth uniform cylinders in synthetic filaments.",
            stepByStepProcedure = listOf(
                "Tease fibre tuft with needle on slide to separate individual filaments.",
                "Place a drop of glycerol mountant on fibres.",
                "Lower cover slip at a 45-degree angle to prevent air bubble formation.",
                "Focus under 10x objective, then switch to 40x for high-magnification analysis.",
                "Sketch longitudinal view and record distinct morphological markings."
            ),
            expectedObservations = "Cotton shows flat ribbon convolutions. Wool shows overlapping surface cuticular scales. Silk shows smooth triangular structure. Polyester shows uniform glass-like rod with titanium dioxide delusterant specks.",
            precautions = listOf("Avoid excess liquid mountant; blot edges with filter paper."),
            vivaQuestions = listOf(
                "What microscopic feature uniquely identifies wool fibres?" to "Cuticular surface scales.",
                "What creates the convolutions in mature cotton fibres?" to "The collapse of the hollow central lumen upon dehydration after boll opening."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 11: Microscopic Fibre Analysis)"
        )
    )

    // BVTD 113: Sewing Techniques (practical) - 4 Credits, P: 75, IA: 25, Total: 100, Page 12
    val sem1PracticalsBvtd113 = listOf(
        PracticalActivity(
            id = "bvtd113_p1_machine_anatomy",
            title = "Single Needle Lockstitch Machine (SNLS): Anatomy, Care & Maintenance",
            subjectCode = "BVTD113",
            objective = "To identify all structural and functional components of industrial and domestic lockstitch machines, perform cleaning and oiling routines, regulate upper/lower thread tensions, and rectify stitch defects.",
            materialsRequired = listOf(
                "Single Needle Lockstitch Machine (SNLS - Juki/Brother/Usha)",
                "White sewing machine lubricating oil (viscosity 10/22)",
                "Soft lint brush, screwdriver, tweezers",
                "Needles (Sizes 11, 14, 16, 18 - Organ/Schmetz)",
                "Muslin fabric swatches (15x15 cm) & 40/2 polyester sewing thread"
            ),
            theory = "The lockstitch (ISO 4915 Type 301) is formed by interlocking the needle thread with the bobbin thread inside the fabric plies. Balanced tension, precise needle timing with the rotary hook, and proper feed dog height are vital for high-speed apparel assembly without seam puckering or skipped stitches.",
            stepByStepProcedure = listOf(
                "Identify external parts: Spool pin, upper thread guides, tension discs, take-up lever, needle clamp, presser foot, feed dog, throat plate, balance wheel, stitch length dial, and reverse lever.",
                "Identify underbed parts: Bobbin case, rotary shuttle hook, feed cam mechanism, oil pan and pump.",
                "Remove throat plate and bobbin case; use brush to remove lint accumulated between feed dog teeth and hook race.",
                "Oil designated lubrication points and rotary hook track (run idle for 30s to distribute).",
                "Insert needle with long groove facing left (operator side) and flat shank towards clamp; tighten screw.",
                "Thread upper path: Spool -> Guides -> Tension discs -> Take-up lever -> Guides -> Needle eye (left to right).",
                "Insert wound bobbin into case with thread paying out clockwise under the tension leaf spring.",
                "Stitch test lines on fabric swatch: check for balanced 301 stitches (knots buried in middle of fabric, 8-10 SPI)."
            ),
            expectedObservations = "Smooth machine operation, balanced stitch tension with no loose loops on face or reverse sides of the fabric.",
            precautions = listOf(
                "Switch off motor power before threading, needle change, or cleaning.",
                "Never run machine with presser foot down directly on feed dogs without fabric in between.",
                "Keep fingers at a minimum 2.5 cm safe distance from moving needle bar."
            ),
            vivaQuestions = listOf(
                "What is the ISO numerical classification for lockstitch?" to "ISO 4915 Stitch Type 301.",
                "What causes loops to appear on the underside of fabric?" to "Loose upper thread tension or missing the thread take-up lever during upper threading.",
                "What is the function of the thread take-up lever?" to "It feeds thread to form the loop for the rotary hook and then pulls excess thread back to tighten the stitch."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 12: Item 1 - Machine Care & Maintenance)"
        ),
        PracticalActivity(
            id = "bvtd113_p2_hand_stitches",
            title = "Basic Hand Sewing Techniques (Temporary & Permanent)",
            subjectCode = "BVTD113",
            objective = "To construct standard temporary basting stitches and permanent couture hand stitches on cotton fabric swatches.",
            materialsRequired = listOf(
                "Cotton cambric/muslin fabric (15x15 cm swatches)",
                "Hand sewing needles (Crewel/Sharps sizes 7-9)",
                "Contrasting cotton sewing thread",
                "Tailor's chalk, measuring tape, embroidery scissors, thimble"
            ),
            theory = "Hand stitches are essential for haute couture garment construction, fitting bastes, delicate edge finishes, and invisible hemming. Temporary stitches hold fabric layers securely during machine sewing and are later removed; permanent stitches remain in the garment.",
            stepByStepProcedure = listOf(
                "Even Basting: Stitch equal stitch and space lengths (approx 6mm) for holding seams firmly.",
                "Uneven Basting: Stitch long stitch (12mm) on upper side and short stitch (3mm) on reverse for general guideline marking.",
                "Diagonal Basting: Work vertical insertion producing slanted surface stitches for securing jacket interfacings.",
                "Running Stitch: Work fine, even in-and-out stitches (2-3mm) for delicate gathers or lightweight seams.",
                "Backstitch: Work overlapping back-and-forth stitches to simulate machine lockstitch strength.",
                "Invisible / Blind Hemming: Catch only one thread of garment fabric and fold of hem to make stitches invisible on face side.",
                "Slip Stitch: Slide needle through fold edge and catch single ground thread for clean couture hems."
            ),
            expectedObservations = "Neat, uniform stitches with consistent spacing and thread tension; zero fabric puckering; invisible hemming stitches unseen on the right side.",
            precautions = listOf(
                "Use a single thread strand for fine hemming to avoid bulk.",
                "Wear a thimble on the middle finger to push needle without finger strain.",
                "Do not pull basting threads too tight, which distorts fabric grain."
            ),
            vivaQuestions = listOf(
                "What is the primary difference between even and uneven basting?" to "Even basting has equal stitch/space lengths for secure seam holding; uneven basting has long surface stitches used for guidelines.",
                "Which hand stitch has highest tensile strength equivalent to machine stitch?" to "The Backstitch.",
                "Where is slip hemming most commonly applied?" to "On high-end garment hems, neck bindings, and attaching jacket linings invisibly."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 12: Item 2 - Hand Sewing Techniques)"
        ),
        PracticalActivity(
            id = "bvtd113_p3_seams",
            title = "Seams & Seam Finishes (Plain, Run & Fell, French, Counter)",
            subjectCode = "BVTD113",
            objective = "To construct and analyze sample swatches of Plain seam with edge finishes, Run & Fell (Flat-Felled) seam, French seam, and Counter (Lapped) seam.",
            materialsRequired = listOf(
                "Medium weight cotton fabric (10x15 cm strips)",
                "Sheer chiffon/organza fabric for French seam sample",
                "Denim/drill fabric for Flat-Felled seam sample",
                "Lockstitch machine, iron, pinking shears & overlock machine"
            ),
            theory = "A seam is a line of stitching joining two or more layers of fabric. The choice of seam depends on fabric weight, durability requirements, garment type, and aesthetic standards. Seam finishes prevent raw edges from fraying.",
            stepByStepProcedure = listOf(
                "Plain Seam: Place fabric right sides together, stitch 1.5 cm (5/8 inch) from edge, press open. Apply Pinked, Turned-and-Stitched, and Overcast finishes.",
                "French Seam: Place fabric WRONG sides together, stitch 6mm from edge, trim allowance to 3mm, press, fold RIGHT sides together, and stitch 6mm from fold to encase raw edge completely.",
                "Run & Fell Seam (Flat-Felled): Stitch plain seam with 1.5cm allowance. Trim one allowance to 4mm. Fold wider allowance over narrow allowance, turn raw edge under, and edgestitch flat to garment.",
                "Counter Seam (Lapped Seam): Turn under raw edge of one piece, lap over right side of second piece, and stitch through all thicknesses."
            ),
            expectedObservations = "Crisp, flat seams with consistent width allowances, clean enclosed edges without fraying threads protruding.",
            precautions = listOf(
                "Trim inner seam allowance accurately in French seams to prevent raw whiskers from sticking out on face side.",
                "Ensure iron temperature matches fabric composition."
            ),
            vivaQuestions = listOf(
                "Why is a French seam suitable for sheer fabrics like chiffon and organza?" to "Because it encases all raw edges within a self-enclosed seam, preventing fraying and looking neat from both sides.",
                "Where is the Run and Fell (Flat-Felled) seam most widely used in the apparel industry?" to "In jeans, denim jackets, workwear, and men's dress shirt side seams due to its extreme durability and flat profile.",
                "What is standard industrial seam allowance for plain seams?" to "1.5 cm (or 5/8 inch)."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 12: Item 3 - Seams & Seam Finishes)"
        ),
        PracticalActivity(
            id = "bvtd113_p4_fullness",
            title = "Control of Fullness (Yoke with Fullness, Gathers, Darts, Pleats)",
            subjectCode = "BVTD113",
            objective = "To draft, manipulate, and construct fullness controls including single pointed darts, French darts, gather distribution, knife/box/inverted pleats, and yoke attachments.",
            materialsRequired = listOf(
                "Cotton poplin fabric swatches",
                "Tailor's chalk, tracing wheel, carbon paper",
                "Sewing machine & pressing ham, pins, iron"
            ),
            theory = "Fullness controls transform flat 2D fabric into 3D shapes that conform to the human body contours or add decorative volume. Darts shape contours at bust and waist; gathers and pleats introduce functional comfort and silhouette drama.",
            stepByStepProcedure = listOf(
                "Darts: Mark dart apex and legs accurately; fold on center line right sides together; stitch from wide base tapering smoothly to point; tie off thread ends without backstitching at apex; press over tailor's ham.",
                "Gathers: Stitch two parallel rows of long basting stitches (SPI 4-6); hold bobbin threads and slide fabric evenly to desired finished width; secure ends in figure-8 around pins.",
                "Pleats: Mark fold lines and placement lines; construct Knife Pleats (turned in one direction), Box Pleats (two folds turned away from each other), and Inverted Box Pleats (two folds meeting at center); press crisp folds.",
                "Yoke with Fullness: Gather lower bodice swatch to match yoke width; pin right sides together, stitch 1.2 cm seam, and press seam allowance upwards towards yoke."
            ),
            expectedObservations = "Smooth dart points without dimpling or puckers; evenly distributed gathers without bunching; sharp, parallel pleats pressed along true straight grain.",
            precautions = listOf(
                "Taper dart stitches into the fold at apex to avoid unsightly puckers or bubbles on the garment face.",
                "Distribute gathers uniformly before final seaming."
            ),
            vivaQuestions = listOf(
                "What is the function of a dart in garment construction?" to "To convert 2D flat fabric into a 3D shape that fits over body curves (such as bust, waist, hips).",
                "What is the difference between a box pleat and an inverted box pleat?" to "In a box pleat, the two folds turn away from each other on the right side; in an inverted pleat, the two folds meet on the right side."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 12: Item 4 - Fullness Controls)"
        ),
        PracticalActivity(
            id = "bvtd113_p5_plackets",
            title = "Plackets (Continuous Bound, French Placket, Extended Placket)",
            subjectCode = "BVTD113",
            objective = "To construct functional garment openings including Continuous bound placket, French (tailored shirt) placket, and Extended facing placket on fabric swatches.",
            materialsRequired = listOf(
                "Cotton shirt fabric swatches (20x20 cm)",
                "Lightweight fusible interfacing",
                "Sewing machine, iron, marking chalk"
            ),
            theory = "A placket is a finished opening in a garment that allows it to be put on or taken off with ease (at neckline, sleeve cuff, or waistline). Plackets provide structure for buttons, snaps, or zipper closures.",
            stepByStepProcedure = listOf(
                "Continuous Bound Placket: Cut a 12cm slash in fabric; cut a straight grain binding strip 3.5cm wide; open slash into a straight line; stitch binding right sides together with 3mm seam tapering at slash apex; fold binding over raw edge and topstitch on stitch line.",
                "French / Tailored Shirt Placket: Cut upper and under placket pieces; interface upper placket; stitch under placket to back edge; attach upper placket to front edge, fold over with pointed bottom tab, and topstitch through all layers forming reinforcement box with 'X' stitch.",
                "Extended Placket: Fold self-facing extension along fold line with interfacing inside; stitch top and bottom seams, turn right side out, and press crisp edge."
            ),
            expectedObservations = "Flat plackets without puckering at the slash point; crisp corners on tailored placket pointed tabs; smooth overlap without gaping.",
            precautions = listOf(
                "Stitch extremely close to the slash edge (1-2mm) at the point of continuous plackets to prevent deep pleats.",
                "Use fusible interfacing for crisp tailored shirt plackets."
            ),
            vivaQuestions = listOf(
                "Where is a continuous bound placket most commonly used?" to "On children's dresses, women's kurti necklines, and sleeve cuff openings of lightweight blouses.",
                "Why is a reinforcement 'X' stitched at the base of a tailored shirt placket?" to "To secure the top and bottom placket overlaps and prevent tearing at the stress point during wear."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 12: Item 5 - Plackets)"
        ),
        PracticalActivity(
            id = "bvtd113_p6_pockets",
            title = "Pockets (Patch, In-Seam, Kurta, Single Welt)",
            subjectCode = "BVTD113",
            objective = "To construct essential pocket variations: Patch pocket with shaped hem, In-seam side pocket, traditional Kurta side pocket, and Single Welt pocket.",
            materialsRequired = listOf(
                "Cotton poplin / drill fabric swatches",
                "Pocket bag lining fabric",
                "Fusible interfacing for welt",
                "Sewing machine, pressing ham, iron"
            ),
            theory = "Pockets provide functional utility and stylistic design details. Patch pockets are topstitched on the garment exterior; in-seam pockets are hidden within side seams; welt pockets feature a reinforced slit opening with an exposed fabric strip.",
            stepByStepProcedure = listOf(
                "Patch Pocket: Fold top hem over interfacing, stitch hem; press seam allowances inward along template; position on garment, pin, and topstitch 1.5mm from folded edge with reinforced bar-tacks at top corners.",
                "In-Seam Pocket: Cut 2 pocket bag pieces; stitch each piece to front and back garment side seam allowances; press seam allowances toward pocket bags; join front and back side seam, stitching around pocket bag contour in one continuous run.",
                "Kurta Pocket: Prepare pocket bag with side mouth opening; attach to side slit opening of kurta side seam with clean topstitching and reinforced bar-tack ends.",
                "Single Welt Pocket: Fuse interfacing on welt strip and pocket location; stitch welt and pocket bag to right side of garment parallel 1cm apart; slash between stitch lines with triangular 'Y' cuts at ends; turn pocket bag through slash to inside; press welt upwards, stitch triangular tabs to welt ends, and stitch pocket bags closed."
            ),
            expectedObservations = "Even, square welt openings with zero gaping; crisp patch pocket corners; smooth in-seam pockets that lie flat against hip contours.",
            precautions = listOf(
                "Cut exactly to the corner stitches when slashing welt openings—do not cut through the thread or stop short.",
                "Reinforce upper pocket corners with bar-tacks to withstand pulling stress."
            ),
            vivaQuestions = listOf(
                "What is a welt pocket?" to "A slash pocket where the opening is finished with a separate fabric band (the welt), widely used on blazers, waistcoats, and trouser backs.",
                "Why are triangular tabs cut at the ends of a welt opening?" to "To allow the pocket bag and welt to turn cleanly to the reverse side without bulk or puckering."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 12: Item 6 - Pockets)"
        ),
        PracticalActivity(
            id = "bvtd113_p7_sleeves",
            title = "Sleeve Variations (Plain, Puff, Bishop, Bell, Cap, Raglan, Kimono, Dolman)",
            subjectCode = "BVTD113",
            objective = "To draft patterns, manipulate fullness, and construct scale samples of sleeve variations including Plain set-in, Puff, Bishop, Bell, Cap, Raglan, Kimono, and Dolman sleeves.",
            materialsRequired = listOf(
                "Pattern paper & grading rulers",
                "Cotton fabric swatches (1/2 scale)",
                "Armhole bodice templates, sewing machine, iron"
            ),
            theory = "Sleeves are classified as Set-in (inserted into an armscye seam), Raglan (extending to neckline with diagonal seam), or Integral / Bodice-cut (Kimono and Dolman where sleeve is continuous with bodice).",
            stepByStepProcedure = listOf(
                "Plain Sleeve: Draft basic sleeve block; ease sleeve cap crown into armscye between front and back notches; stitch 1.2cm seam with ease distributed over crown.",
                "Puff Sleeve: Slash and spread sleeve pattern horizontally and vertically; gather crown and sleeve hem; attach cuff band.",
                "Bishop Sleeve: Slash and spread towards hemline; gather lower edge into fitted cuff band.",
                "Bell Sleeve: Flare lower hemline smoothly without gathering.",
                "Cap Sleeve: Short sleeve covering only shoulder cap and tapering to zero at underarm.",
                "Raglan Sleeve: Draft diagonal seam extending from underarm to neckline; assemble front/back raglan seams.",
                "Kimono / Dolman: Extend shoulder line outwards with underarm gusset or curved deep armhole continuous with bodice."
            ),
            expectedObservations = "Smooth sleeve cap crown without pleating or dimples; symmetrical puff gathers; clean underarm transitions in Kimono/Raglan.",
            precautions = listOf(
                "Always match front and back balance notches accurately.",
                "Clip curved underarm seams and press seam allowances towards the sleeve cap."
            ),
            vivaQuestions = listOf(
                "What is the difference between a Set-in sleeve and a Raglan sleeve?" to "A set-in sleeve is joined at the armhole seam around the shoulder; a raglan sleeve extends to the neckline with a diagonal seam from underarm to collar.",
                "What is sleeve cap ease?" to "The extra 2-3 cm of circumference in the sleeve cap compared to the armhole, easing over the shoulder mound."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 12: Item 7 - Sleeve Variations)"
        ),
        PracticalActivity(
            id = "bvtd113_p8_collars",
            title = "Collar Variations (Flat Peter Pan, Shawl, Mandarin, Cape Collar)",
            subjectCode = "BVTD113",
            objective = "To draft, interface, construct, and attach collar variations: Flat Peter Pan collar, Standing Mandarin collar, Rolled Shawl collar, and Cape collar.",
            materialsRequired = listOf(
                "Pattern paper, muslin / poplin fabric",
                "Fusible collar canvas / interfacing",
                "Sewing machine, point turner, iron"
            ),
            theory = "Collars frame the neckline and face. They are classified into Flat collars (lie flat on body without stand), Standing collars (stand upright around neck), and Rolled collars (stand at back and roll over to front lapel).",
            stepByStepProcedure = listOf(
                "Peter Pan Collar: Draft using front and back neckline templates overlapping shoulder by 1.2cm; interface upper collar; stitch outer curved edges right sides together, trim, notch curve, turn right side out, and attach to neckline with bias strip facing.",
                "Mandarin Collar: Draft curved standing band; fuse interfacing; stitch top and curved ends; attach outer collar to garment neckline, press seam allowance inside collar, and edgestitch inner collar closed.",
                "Shawl Collar: Draft collar extension continuous with bodice front lapel; join center back collar seam; roll collar over roll line and attach to back neckline.",
                "Cape Collar: Draft circular flared collar extending over shoulders; finish outer edge with rolled hem or lace; attach to neckline."
            ),
            expectedObservations = "Symmetrical collar points and curves; crisp roll line; flat collar sitting smooth over shoulder without curling upward.",
            precautions = listOf(
                "Trim seam allowances and notch convex curves before turning right side out.",
                "Interface the upper collar to prevent seam allowance show-through."
            ),
            vivaQuestions = listOf(
                "What category of collar is a Peter Pan collar?" to "A Flat collar.",
                "What is the function of collar interfacing?" to "To provide body, crisp structure, roll stability, and prevent neckline stretching."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 12: Item 8 - Collar Variations)"
        ),
        PracticalActivity(
            id = "bvtd113_p9_kids_dress",
            title = "Kids Dress Design & Construction Project",
            subjectCode = "BVTD113",
            objective = "To design, draft patterns, cut, and construct a complete children's wear garment (A-line frock / gathered yoke dress / kurta) integrating yoke, fullness, collar, sleeve, placket, and pocket fashion details.",
            materialsRequired = listOf(
                "Printed / plain cotton poplin fabric (1.5 meters)",
                "Contrasting fabric for trims and Peter Pan collar",
                "Buttons / snap fasteners, fusible interfacing, matching thread",
                "Full sewing workstation, iron"
            ),
            theory = "Garment construction integrates individual fashion components into a functional, comfortable, and aesthetically delightful apparel product suitable for children's body measurements and active movement.",
            stepByStepProcedure = listOf(
                "Develop design sketch and technical specification sheet for children's wear (Age 3-5 years).",
                "Draft pattern blocks: Front yoke, back yoke, gathered lower skirt, puff sleeve, Peter Pan collar, patch pocket, and continuous back placket.",
                "Lay patterns on straight grain of pre-shrunk cotton fabric, add seam allowances, and cut pieces.",
                "Construct and attach patch pocket to front skirt.",
                "Gather lower skirt panels and attach to front and back yokes.",
                "Construct continuous bound placket at back neckline.",
                "Join shoulder seams with plain seams and overlock finish.",
                "Prepare and attach Peter Pan collar with neat bias binding.",
                "Prepare gathered puff sleeves and set into armholes.",
                "Stitch continuous side seams from sleeve hem to garment bottom.",
                "Turn up and stitch bottom hem; attach buttons and fasteners."
            ),
            expectedObservations = "A well-balanced, comfortable kids garment with clean seam finishes, functional back placket, symmetrical collar, and charming aesthetic appeal.",
            precautions = listOf(
                "Ensure all internal raw edges are cleanly enclosed or overlocked to avoid irritating delicate children's skin.",
                "Securely attach buttons and small trims with reinforced stitching for child safety standards."
            ),
            vivaQuestions = listOf(
                "Why is cotton the preferred fabric for children's wear construction?" to "It is breathable, hypoallergenic, highly absorbent, soft on delicate skin, and easy to wash.",
                "What safety factors must be considered in childrenswear garment engineering?" to "Avoid loose cords or long drawstrings around necklines, securely fasten buttons, and eliminate rough internal seams."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 12: Item 9 - Kids Dress Design Project)"
        )
    )

    // =========================================================================
    // SEMESTER 2 PRACTICALS
    // =========================================================================

    // BVTD 122: Garment sewing(practical) - 4 Credits, P: 75, IA: 25, Total: 100, Page 30
    val sem2PracticalsBvtd122 = listOf(
        PracticalActivity(
            id = "bvtd122_p1_bodice_drafting",
            title = "Adult Bodice Block Drafting & Measurement Anthropometry",
            subjectCode = "BVTD122",
            objective = "To take accurate anthropometric body measurements and draft standard front and back foundation adult bodice blocks with accurate ease allowances.",
            materialsRequired = listOf(
                "Pattern drafting brown paper / metric paper",
                "Grading rulers (L-square, French curves, hip curve, armscye ruler)",
                "Measuring tape, pencil, paper shears",
                "Dress form (Size 34/36)"
            ),
            theory = "A foundation block (sloper) is the 2D template reflecting human body proportions without design style fullness. Correct measurement taking (bust, waist, across back, nape to waist) and proportional ease allowances ensure optimal garment fit.",
            stepByStepProcedure = listOf(
                "Record key anatomical measurements: Bust circumference, Waist, Neck circumference, Shoulder length, Across Front/Back, and Nape-to-Waist length.",
                "Draft Back Bodice Block: Construct guide rectangle based on 1/4 bust + ease; mark back neck curve (7cm x 2cm); draft shoulder slope and back armscye curve.",
                "Draft Front Bodice Block: Mark front neck drop (7cm x 7.5cm); construct front shoulder and deeper armscye curve using French curve; calculate and mark shoulder bust dart and waist contour dart.",
                "Check seam alignment: Walk side seams and shoulder seams of front and back blocks to verify exact length matching.",
                "True all dart legs and mark balance notches and grainline arrows."
            ),
            expectedObservations = "Smooth, continuous armscye and neckline contours without flat spots; accurate dart intake matching bust cup prominence.",
            precautions = listOf(
                "Ensure pencil lines are razor-sharp to prevent cumulative drafting errors.",
                "Verify that armscye depth matches the wearer's comfort ease requirements."
            ),
            vivaQuestions = listOf(
                "What is the difference between a foundation block (sloper) and a working pattern?" to "A sloper is a basic fit template without seam allowances or style lines; a working pattern includes style features, seam allowances, and production details.",
                "What is the standard ease allowance added to bust circumference in a basic fitted bodice?" to "Approx 4 to 6 cm (1.5 to 2.5 inches) for standard breathing and movement comfort."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 30: Bodice Block Drafting)"
        ),
        PracticalActivity(
            id = "bvtd122_p2_dart_manipulation",
            title = "Dart Manipulation Techniques (Pivotal & Slash-and-Spread Methods)",
            subjectCode = "BVTD122",
            objective = "To relocate bust darts from shoulder to various perimeter positions: French dart, side seam underarm dart, center front waist dart, neckline dart, and armhole dart.",
            materialsRequired = listOf(
                "Basic front bodice sloper template on cardstock",
                "Pattern paper, tracing wheel, pins, paper tape, pencil",
                "French curve and ruler"
            ),
            theory = "Dart manipulation is the foundational principle of flat pattern design. Since the bust mound is 3D, all dart intake radiating from the bust apex (pivot point) can be rotated to any position along the block perimeter without changing the garment's fit.",
            stepByStepProcedure = listOf(
                "Pivotal Method: Pin front sloper at bust apex point on pattern paper; trace block outline from new dart location clockwise to old dart leg; close old dart by rotating sloper; trace remaining block outline; draw new dart legs to apex.",
                "Slash-and-Spread Method: Draw slash line from new position to apex; cut along line up to apex; close original shoulder dart with tape; spread open the slash to create new dart intake; place paper underneath and true dart legs.",
                "Back off dart apex: Shorten finished stitched dart point by 2 to 2.5 cm from the apex to avoid a sharp cone peak on the bust.",
                "Construct muslin test swatches for French dart and Side seam underarm dart to verify 3D fit on dress form."
            ),
            expectedObservations = "Clean 3D cup formation over the bust point with smooth fabric drape and zero puckers at dart tips.",
            precautions = listOf(
                "Always pivot strictly from the exact bust apex point.",
                "Never let the stitched dart point touch the actual apex; always back off 2 cm."
            ),
            vivaQuestions = listOf(
                "What is the pivotal method of dart manipulation?" to "Rotating the entire sloper around the bust apex to transfer dart volume to a new position without cutting the master template.",
                "What is a French dart?" to "A combined side/waist dart starting from low on the side seam and angling diagonally upward toward the bust apex."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 30: Dart Manipulation Lab)"
        ),
        PracticalActivity(
            id = "bvtd122_p3_garment_assembly",
            title = "Complete Garment Construction: Women's Designer Kurti / Tunic",
            subjectCode = "BVTD122",
            objective = "To construct a complete women's designer Kurti / tunic featuring styled neckline, front placket, set-in sleeves with cuffs, and finished side slits.",
            materialsRequired = listOf(
                "Cotton / Chanderi fabric (2.25 meters)",
                "Matching sewing thread, fusible neckline interfacing",
                "Lockstitch machine, overlock machine, iron, buttons"
            ),
            theory = "Full garment assembly tests cutting layout, grainline accuracy, sequential assembly line balancing, seam matching, neckline finishing with fused facings, and side vent corner engineering.",
            stepByStepProcedure = listOf(
                "Develop pattern with side waist shaping and flared hem with side slits.",
                "Perform straight grain fabric layout with 1.5cm seam allowances and 3cm hem allowance; cut pieces.",
                "Stitch front and back waist darts; press toward center.",
                "Interface and construct neckline facing/placket; attach to front neckline and topstitch.",
                "Join shoulder seams; press allowances open and overlock.",
                "Construct sleeves, attach cuff bands, and set sleeves into armholes on flat or in-the-round.",
                "Stitch side seams down to side slit notch.",
                "Turn and edgestitch side slit margins with reinforced bar-tack across top slit apex.",
                "Turn up bottom hems; attach decorative buttons on front placket."
            ),
            expectedObservations = "A well-fitted, balanced ethnic tunic with symmetrical side slits, flat neckline facing, and clean internal overlocking.",
            precautions = listOf(
                "Reinforce side slit apex with a horizontal bar-tack to prevent seam splitting during movement.",
                "Ensure grainlines run true vertical along center front and center back."
            ),
            vivaQuestions = listOf(
                "Why is a bar-tack stitched at the top of a kurta side slit?" to "To absorb pulling strain and prevent the side seam from ripping during walking or sitting.",
                "What is the purpose of neckline understitching?" to "To keep the facing rolled to the inside so it remains unseen on the right side of the garment."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 30: Complete Garment Sewing Project)"
        )
    )

    // BVTD 123: Design foundation and basics of textiles – II(Practical) - 4 Credits, P: 75, IA: 25, Total: 100, Page 31
    val sem2PracticalsBvtd123 = listOf(
        PracticalActivity(
            id = "bvtd123_p1_weave_analysis",
            title = "Woven Fabric Weave Analysis & Design Representation",
            subjectCode = "BVTD123",
            objective = "To analyze woven fabric samples, dissect warp and weft interlacement, and represent Plain, Twill (2/1, 2/2), Satin, Sateen, and Honeycomb weaves with Drafting, Lifting, and Denting plans on point paper.",
            materialsRequired = listOf(
                "Point / Grid design paper (8x8 or 10x10 grid)",
                "Counting needle / Dissecting pick glass",
                "Fabric swatches: Plain cotton, 2/2 Twill denim/gabardine, Satin, Honeycomb towel fabric",
                "Color pencils (Red for warp lifts, black for grid outline)"
            ),
            theory = "Woven fabrics are produced by interlacing warp (longitudinal) and weft (transverse) yarns at right angles. The weave design indicates warp lifts (shaded square) and weft floats (blank square). The drafting plan shows heald shaft threading, while the lifting plan determines harness movement sequence.",
            stepByStepProcedure = listOf(
                "Identify warp direction (selvedge parallel, higher twist, sizing marks).",
                "Unravel warp and weft threads systematically using counting needle; record interlacement sequence over 1 repeat.",
                "Plot weave repeat on point paper: Shaded square = Warp up / over weft; Blank square = Weft up / over warp.",
                "Construct Drafting Plan: Assign a separate heald shaft to every warp yarn having a distinct interlacement order.",
                "Construct Lifting Plan: Indicate which heald frames are raised on each pick insertion.",
                "Construct Denting Plan: Show number of ends drawn through each reed dent."
            ),
            expectedObservations = "Accurate point paper graphs showing 1/1 Plain repeat (2x2), 2/2 Twill repeat (4x4) with continuous diagonal twill lines, and 5-end Satin repeat (5x5) without touching points.",
            precautions = listOf(
                "Ensure exactly one complete weave repeat is identified before plotting drafting plans.",
                "Always place warp ends on vertical columns and weft picks on horizontal rows."
            ),
            vivaQuestions = listOf(
                "What is the smallest repeat size for a plain weave?" to "2 ends x 2 picks (2x2).",
                "What is the minimum number of heald shafts required to weave a 2/2 twill?" to "4 heald shafts (Straight draft).",
                "Why do satin weaves have smooth, lustrous surfaces?" to "Because long filament floats conceal interlacements without visible diagonal twill lines."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 31: Woven Fabric Weave Analysis)"
        ),
        PracticalActivity(
            id = "bvtd123_p2_tie_dye",
            title = "Traditional Resist Dyeing: Tie & Dye (Bandhani & Shibori Techniques)",
            subjectCode = "BVTD123",
            objective = "To prepare cotton fabric swatches and execute traditional resist dyeing methods including Knotting, Marbling, Tritik (stitched resist), Folding (Itajime clamp resist), and Arashi pole wrapping.",
            materialsRequired = listOf(
                "Desized 100% cotton cambric fabric swatches (30x30 cm)",
                "Direct / Reactive cold-water dyes (Procion M/H dyes)",
                "Soda ash (Sodium carbonate), Common salt (NaCl)",
                "Nylon tying thread, marbles, wooden clamp blocks, C-clamps, PVC pipe",
                "Dye baths, measuring beakers, rubber gloves"
            ),
            theory = "Tie and dye is a resist dyeing method where physical pressure (binding, clamping, or stitching) prevents dye liquor from penetrating the bound areas, resulting in vibrant contrasting motifs on the fabric surface.",
            stepByStepProcedure = listOf(
                "Scour and wet out cotton swatches in warm water to ensure uniform absorbency.",
                "Bandhani: Pinch tiny fabric points and bind tightly with nylon thread in concentric rings.",
                "Itajime Shibori: Accordion-fold fabric into triangles or squares; clamp between two identical wooden shapes using C-clamps.",
                "Arashi: Wrap fabric around PVC pipe diagonally, bind with string, and scrunch tightly along the pipe.",
                "Prepare dye bath with reactive dye (3-5% owf), common salt (30 g/L), and soda ash alkali (10 g/L).",
                "Immerse bound samples for 45 minutes; rinse thoroughly in cold water.",
                "Unbind threads, wash with mild detergent, rinse, and dry in shade to reveal resist patterns."
            ),
            expectedObservations = "High-contrast, sharp resist boundaries with characteristic organic dye seepage halos.",
            precautions = listOf(
                "Tie knots extremely tight to prevent dye bleeding into resist centers.",
                "Wear rubber gloves and protective apron when handling reactive dye liquors."
            ),
            vivaQuestions = listOf(
                "What is the role of soda ash in reactive dyeing of cotton?" to "It provides an alkaline pH (10.5-11.0) necessary for the covalent chemical bond between reactive dye molecules and cellulose hydroxyl groups.",
                "What is the meaning of 'Shibori'?" to "A Japanese term referring to various manual resist-dyeing techniques (shiboru = to wring, squeeze, press)."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 31: Tie & Dye and Shibori Lab)"
        ),
        PracticalActivity(
            id = "bvtd123_p3_batik_printing",
            title = "Batik Art (Wax Resist) & Block/Screen Printing",
            subjectCode = "BVTD123",
            objective = "To execute hot wax resist Batik art on cotton fabric using paraffin/beeswax mixture, create signature crackle texture, and print repeat motifs using wooden blocks and screen frames.",
            materialsRequired = listOf(
                "White cotton fabric swatches",
                "Paraffin wax and Beeswax (60:40 ratio), Electric wax melting pot",
                "Tjanting tools and bristle brushes",
                "Carved wooden printing blocks, binder paste, pigment colors",
                "Newspaper sheets, hot iron for dewaxing"
            ),
            theory = "Batik uses molten wax as a mechanical resist against cold dye baths. Controlled crushing of cooled wax creates distinctive fine crackle veins. Block and screen printing apply pigment pastes directly to fabric through relief carving or stencil mesh.",
            stepByStepProcedure = listOf(
                "Melt 60% paraffin and 40% beeswax at 70-80°C in wax pot.",
                "Trace motif on cotton; apply hot molten wax using tjanting tool or brush so wax penetrates fully to the back side.",
                "Cool sample in cold water; gently crush wax between fingers to create crackle fractures.",
                "Dip in cold dye bath (Napthol / cold reactive dye) for 15 minutes.",
                "Remove, rinse, and place between sheets of newspaper; iron with hot iron to melt and absorb wax into paper.",
                "Boil in hot water with detergent for complete dewaxing.",
                "Block Printing: Dip carved wooden block in pigment paste; print uniform repeat alignment on cotton swatch using mallet pressure."
            ),
            expectedObservations = "Exquisite multi-toned batik motifs with authentic crackle effect; sharp, clean block print repeats without smudging.",
            precautions = listOf(
                "Never overheat wax on open flame (fire hazard); use temperature-regulated wax pots.",
                "Ensure wax temperature is hot enough to penetrate both sides of the fabric completely."
            ),
            vivaQuestions = listOf(
                "Why is beeswax mixed with paraffin wax in batik?" to "Paraffin is brittle and provides crackles; beeswax provides flexibility and adhesion so wax does not chip off prematurely.",
                "What tool is traditionally used in batik for drawing fine wax outlines?" to "The Tjanting (Canting) tool with a copper reservoir and fine spout."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 31: Batik & Printing Lab)"
        ),
        PracticalActivity(
            id = "bvtd123_p4_fabric_testing",
            title = "Textile Testing: Fabric GSM, Yarn Count & Ends/Picks Per Inch",
            subjectCode = "BVTD123",
            objective = "To determine Fabric Grams per Square Meter (GSM) using circular GSM cutter, measure yarn count using Beesley's balance, and calculate Ends and Picks Per Inch (EPI/PPI) using pick glass.",
            materialsRequired = listOf(
                "Circular GSM cutter (100 cm² area) and cutting rubber pad",
                "High precision digital electronic balance (0.01g accuracy)",
                "Pick glass / Counting magnifying glass & dissecting needle",
                "Beesley's yarn count balance & template",
                "Assorted woven fabric swatches (Cotton poplin, Denim, Silk, Polyester)"
            ),
            theory = "GSM (Grams per Square Meter) measures fabric areal density and weight. Yarn count (Ne / Tex) defines yarn fineness. EPI and PPI quantify fabric tightness and thread density.",
            stepByStepProcedure = listOf(
                "GSM Determination: Place fabric smoothly on cutting pad; rotate GSM cutter clockwise to cut exact 100 cm² circular swatch; weigh on digital balance; multiply weight in grams by 100 to obtain GSM (g/m²).",
                "Thread Density (EPI/PPI): Place 1-inch aperture pick glass on fabric parallel to warp; count number of warp ends across 1 inch; rotate 90 degrees and count weft picks across 1 inch; repeat at 3 locations and average.",
                "Yarn Count by Beesley Balance: Cut standard length yarn using template; hook yarn ends one by one onto balance arm until pointer balances exactly on zero datum mark; read count directly on scale."
            ),
            expectedObservations = "Accurate quantitative specifications: e.g., Cotton Poplin = 120 GSM, EPI = 80, PPI = 72, Warp Count = 40s Ne.",
            precautions = listOf(
                "Condition fabric samples at standard textile testing atmosphere (65% ± 2% RH and 20°C ± 2°C) prior to weighing.",
                "Never cut GSM samples near fabric selvedges."
            ),
            vivaQuestions = listOf(
                "What is the mathematical relationship between sample weight from a 100 cm² GSM cutter and fabric GSM?" to "GSM = Weight of 100 cm² circle (in grams) x 100.",
                "What is the difference between Direct and Indirect yarn numbering systems?" to "In Direct systems (Tex, Denier), count increases as yarn becomes thicker; in Indirect systems (Ne, Nm), count increases as yarn becomes finer."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 31: Textile Testing & Fabric Analysis)"
        )
    )

    // CS-BVTD121: Computer Applications-II(Practical) - 4 Credits, P: 75, IA: 25, Total: 100, Page 34-35
    val sem2PracticalsCsBvtd121 = listOf(
        PracticalActivity(
            id = "csbvtd121_p1_coreldraw_flats",
            title = "Vector Fashion Illustration & Flat Sketches in CorelDRAW",
            subjectCode = "CS-BVTD121",
            objective = "To utilize CorelDRAW vector tools (Bézier, Freehand, Shape tool, Smart Fill) to draw proportionate 9-head fashion croquis, render technical flat sketches of shirts, trousers, jackets, and create garment spec sheets.",
            materialsRequired = listOf(
                "Computer workstation with CorelDRAW Graphics Suite",
                "Graphic tablet / Stylus (Wacom/Huion)",
                "Standard fashion measurement specification templates"
            ),
            theory = "Vector illustrations maintain mathematical resolution independence at any zoom level. Technical flats (working drawings) are universal communication blueprints between fashion designers, pattern makers, and garment export factories.",
            stepByStepProcedure = listOf(
                "Setup page dimensions and grid snapping rules in CorelDRAW.",
                "Import 9-head body grid template on a locked background layer.",
                "Use Bézier curve tool to draw symmetrical garment silhouettes (dress, shirt, jacket); use Mirror tool across center guideline.",
                "Apply node editing with Shape Tool to refine waist curves, lapel roll lines, and armhole contours.",
                "Add realistic stitching lines using dashed stroke outlines (topstitching, twin needle seams).",
                "Add interactive color fills, gradient shading, and texture patterns.",
                "Add dimension callouts, stitch specification notes, and Bill of Materials (BOM) table."
            ),
            expectedObservations = "Clean, production-ready vector flat sketches with exact symmetry, sharp node paths, and detailed construction callouts.",
            precautions = listOf(
                "Always close open vector paths before applying Smart Fill or pattern fills.",
                "Group related garment components (Collar group, Sleeve group, Bodice group) into separate layers."
            ),
            vivaQuestions = listOf(
                "Why are vector graphics preferred over raster images for fashion flat sketches?" to "Because vectors are resolution-independent, scalable to any size without pixelation, and easily editable by manipulating bezier nodes.",
                "What is a Tech Pack in the apparel industry?" to "A technical specification package containing flat sketches, BOM, size specs, stitch types, tolerance tables, and colorway codes for garment manufacturing."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 34-35: CorelDRAW Fashion CAD Lab)"
        ),
        PracticalActivity(
            id = "csbvtd121_p2_photoshop_textiles",
            title = "Digital Textile Print Design & Mood Boards in Adobe Photoshop",
            subjectCode = "CS-BVTD121",
            objective = "To conceptualize and design seamless textile print repeats (Straight, Half-drop, Brick repeats), extract color palettes, and create high-fashion concept mood boards using Adobe Photoshop.",
            materialsRequired = listOf(
                "Workstation with Adobe Photoshop CC",
                "High-resolution scanned hand-painted motif library",
                "Digital drawing tablet"
            ),
            theory = "Raster software enables complex photographic manipulation, digital painting, color separation, and seamless pattern tile generation for digital textile printing machines.",
            stepByStepProcedure = listOf(
                "Create a high-resolution canvas (300 DPI, RGB/CMYK).",
                "Import scanned floral/geometric motifs; use Pen tool and Magic Wand to mask clean motif cutouts.",
                "Use Offset Filter (Filter > Other > Offset by half canvas width and height) to wrap motif edges seamlessly across canvas borders.",
                "Fill empty center areas with secondary coordinating motifs.",
                "Define pattern (Edit > Define Pattern) and test seamless repeat tiling on large garment mockups.",
                "Build digital trend mood board: Arrange inspiration images, fabric swatch textures, Pantone color chips, and fashion figures on layered artboards."
            ),
            expectedObservations = "Flawless seamless print pattern with zero visible tile seam joints across continuous repeat surfaces.",
            precautions = listOf(
                "Always design textile prints at minimum 300 DPI resolution for high-definition digital printing.",
                "Save working files with unmerged layers (PSD format) for future colorway adjustments."
            ),
            vivaQuestions = listOf(
                "What is a Half-Drop repeat in textile surface design?" to "A repeat arrangement where every adjacent vertical column drops down by exactly half the height of the repeat unit, preventing visual horizontal banding.",
                "What filter in Photoshop is used to create seamless tile repeats?" to "The Offset Filter (Filter > Other > Offset)."
            ),
            isOfficialSyllabusPractical = true,
            sourceLabel = "Official Syllabus (Page 34-35: Photoshop Textile CAD Lab)"
        )
    )

    // =========================================================================
    // COMPLETE OFFICIAL SEMESTER 1 SUBJECTS (8 Subjects: Theory & Practical)
    // =========================================================================
    private val semester1Subjects = listOf(
        // 1. BVTD 111: Design Foundation & Basics of Textile (Major - Theory)
        Subject(
            code = "BVTD111",
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
            overview = "Foundational theory covering design elements, design principles, classification of textile fibres, yarn formation, and basic textile terminology essential for vocational designers.",
            timeDurationHours = 3,
            mediumOfExam = "English, Hindi, Punjabi",
            instructionsForPaperSetters = "The question paper will consist of five sections: Section A (compulsory short questions) and Sections B, C, D, E covering Units I to IV. Students must attempt one question from each unit.",
            courseObjectives = listOf(
                "To impart fundamental knowledge of design elements and principles.",
                "To classify natural, regenerated, and synthetic textile fibres.",
                "To understand yarn numbering systems and fabric structure basics."
            ),
            learningObjectives = listOf(
                "Understand fundamental elements (Line, Shape, Form, Color, Texture).",
                "Apply principles of design (Balance, Proportion, Rhythm, Emphasis, Harmony).",
                "Classify natural and man-made textile fibres according to botanical, animal, and chemical origins."
            ),
            courseOutcomes = listOf(
                "COS1: Explain visual design principles and color harmony systems.",
                "COS2: Identify fibres based on chemical origin and physical properties.",
                "COS3: Evaluate yarn parameters and basic weave structures."
            ),
            booksPrescribed = listOf(
                "Textile Science by Bernard P. Corbman, McGraw-Hill.",
                "Understanding Textiles by Phyllis G. Tortora, Pearson.",
                "Elements of Design by Joseph A. Saccardi, Fairchild Books."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Elements & Principles of Design",
                    description = "Core visual vocabulary: line, form, space, balance, rhythm, harmony, and proportion.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd111_u1_t1",
                            title = "Elements of Design: Line, Shape, Texture & Color",
                            unitNumber = 1,
                            overview = "The building blocks of visual design. In textile design, lines create stripes and silhouettes, textures define fabric hand-feel, and color evokes emotional response.",
                            keyPoints = listOf(
                                "Lines can be structural or decorative (vertical adds height, horizontal adds width).",
                                "Shapes are 2D geometric or organic; Forms possess 3D volume in draping.",
                                "Texture can be tactile (physical weave surface) or visual (printed pattern).",
                                "Color includes Hue, Value (lightness), and Chroma/Intensity (saturation)."
                            ),
                            importantTerms = mapOf(
                                "Hue" to "The pure name of a color in the spectrum.",
                                "Value" to "The lightness or darkness of a hue relative to black and white.",
                                "Chroma" to "The purity, vividness, or saturation level of a color.",
                                "Tactile Texture" to "The actual physical surface feel of textile materials."
                            ),
                            visualExplanation = "Interactive representation of vertical, horizontal, diagonal, and curved lines showing silhouette elongation in apparel.",
                            industrialRelevance = "Used daily by textile designers to conceptualize print repeats, jacquard weaves, and garment collections.",
                            quickRevisionSummary = "Design elements are tools: Line, Shape, Form, Space, Texture, and Color."
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
                                "Asymmetrical Balance" to "Dynamic balance achieved through unequal visual weights.",
                                "Golden Ratio" to "Mathematical proportion (approx 1:1.618) universally perceived as harmonious."
                            ),
                            visualExplanation = "Diagrams showing symmetrical vs asymmetrical garment bodices and print motif distributions.",
                            industrialRelevance = "Ensures aesthetic balance in high-fashion collections and commercial apparel lines.",
                            quickRevisionSummary = "Principles organize elements: Balance, Proportion, Emphasis, Rhythm, Harmony."
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Introduction to Textile Fibres & Classification",
                    description = "Classification of fibres according to origin: Natural (Cellulose, Protein, Mineral) and Man-Made (Regenerated, Synthetic).",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd111_u2_t1",
                            title = "Classification & Origin of Textile Fibres",
                            unitNumber = 2,
                            overview = "Textile fibres are hair-like units of matter possessing high length-to-width ratio, flexibility, and strength. They form the foundational raw material for all yarns and fabrics.",
                            keyPoints = listOf(
                                "Natural Vegetable (Cellulosic): Cotton (seed), Flax/Linen (bast), Jute (bast), Hemp.",
                                "Natural Animal (Protein): Wool (sheep fleece), Silk (silkworm cocoon filament).",
                                "Regenerated (Semi-synthetic): Viscose Rayon, Modal, Lyocell, Acetate.",
                                "Synthetic Polymers: Polyester (PET), Nylon (Polyamide), Acrylic, Spandex (Elastane)."
                            ),
                            importantTerms = mapOf(
                                "Staple Fibre" to "Short-length fibres measured in inches or centimeters (e.g., cotton, wool).",
                                "Filament Fibre" to "Continuous strands of indefinite length measured in meters or kilometers (e.g., silk, polyester)."
                            ),
                            visualExplanation = "Fibre family tree classification chart showing natural vs chemical synthesis pathways.",
                            industrialRelevance = "Guides raw material selection for spinners, knitters, weavers, and fashion merchandisers."
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 3,
                    title = "Fibre Properties & Physical Characteristics",
                    description = "Morphological structure, tensile strength, elongation, moisture regain, and chemical resistance.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd111_u3_t1",
                            title = "Morphology & Performance Properties of Major Fibres",
                            unitNumber = 3,
                            overview = "Detailed analysis of cotton, wool, silk, and polyester fibres regarding microscopic appearance, burning behavior, and moisture regain.",
                            keyPoints = listOf(
                                "Cotton: Ribbon-like convolutions, 8.5% moisture regain, stronger when wet.",
                                "Wool: Cuticle scales, crimp structure, 15% moisture regain, natural elasticity.",
                                "Silk: Triangular rounded cross-section, natural sheen, high tensile strength.",
                                "Polyester: Smooth cylindrical filament, 0.4% moisture regain, hydrophobic, wrinkle-resistant."
                            ),
                            importantTerms = mapOf(
                                "Moisture Regain" to "The percentage of moisture that bone-dry fibre absorbs from standard atmosphere.",
                                "Tenacity" to "The strength of a fibre per unit linear density (expressed in g/denier or cN/tex)."
                            )
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 4,
                    title = "Yarn Formation & Yarn Numbering Systems",
                    description = "Yarn spinning basics, twist direction (S and Z twist), and Direct/Indirect yarn count systems.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd111_u4_t1",
                            title = "Yarn Classification & Count Calculations",
                            unitNumber = 4,
                            overview = "Spinning transforms staple fibres into continuous yarns through carding, drawing, roving, and twisting. Count represents linear density.",
                            keyPoints = listOf(
                                "Direct Count Systems: Tex (weight in grams of 1,000m), Denier (weight in grams of 9,000m). Higher number = Coarser yarn.",
                                "Indirect Count Systems: English Cotton Count (Ne = number of 840-yard hanks per pound). Higher number = Finer yarn.",
                                "Twist: S-twist (clockwise) and Z-twist (counter-clockwise); TPI (Twist Per Inch) dictates strength and luster."
                            ),
                            importantTerms = mapOf(
                                "Direct System" to "System where yarn count is expressed as mass per unit length.",
                                "Indirect System" to "System where yarn count is expressed as length per unit mass."
                            )
                        )
                    )
                )
            )
        ),

        // 2. BVTD 112: Design Foundation & Basics of Textile (practical) - Major - Practical
        Subject(
            code = "BVTD112",
            name = "Design Foundation & Basics of Textile (practical)",
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
            overview = "Practical laboratory modules covering color wheel creation, value/intensity scales, color schemes, fibre burning identification, microscopic analysis, and fabric swatches.",
            practicals = sem1PracticalsBvtd112,
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Color Theory & Design Composition Lab",
                    description = "12-hue color wheel, tints, shades, tones, and 5 color schemes.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Textile Testing & Fibre Identification Lab",
                    description = "Burning tests, microscopic slides, and commercial fabric swatch mounting.",
                    isOfficialUnit = true
                )
            )
        ),

        // 3. BVTD 113: Sewing Techniques (practical) - Major - Practical
        Subject(
            code = "BVTD113",
            name = "Sewing Techniques (practical)",
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
            overview = "Official practical curriculum covering sewing machine anatomy, care, basic hand stitches, seams & seam finishes, fullness controls, plackets, pockets, sleeve variations, collar variations, and kidswear dress design project.",
            timeDurationHours = 3,
            mediumOfExam = "Practical Demonstration & Viva Voce",
            practicals = sem1PracticalsBvtd113,
            booksPrescribed = listOf(
                "The Art of Couture Sewing by Zoya Nudelman, Bloomsbury Academic.",
                "The Sewing Book by Alison Smith, DK Publishing.",
                "Complete Guide to Sewing, Reader's Digest."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Sewing Machine Operation & Basic Stitches",
                    description = "Lockstitch anatomy, threading, care, and temporary/permanent hand stitches.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Seam Engineering & Fullness Controls",
                    description = "Plain, French, Flat-Felled, Counter seams; Darts, Gathers, Pleats, and Yokes.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 3,
                    title = "Garment Components: Plackets, Pockets, Sleeves & Collars",
                    description = "Continuous/French plackets; Patch/In-seam/Welt pockets; 8 Sleeve styles; 4 Collar variations.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 4,
                    title = "Kids Garment Design & Assembly Project",
                    description = "Full garment drafting, cutting, stitching, and finishing.",
                    isOfficialUnit = true
                )
            )
        ),

        // 4. BVTD 114: Introduction to Enterpenureship (Minor - Theory)
        Subject(
            code = "BVTD114",
            name = "Introduction to Entrepreneurship",
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
            overview = "Minor foundation course covering concepts, competencies, institutional support, project formulation, and enterprise creation for prospective textile and fashion entrepreneurs.",
            timeDurationHours = 3,
            mediumOfExam = "English, Hindi, Punjabi",
            instructionsForPaperSetters = "The question paper consists of five sections: Section A (compulsory short questions) and Sections B, C, D, E corresponding to Units I to IV. Students attempt one question from each unit.",
            courseObjectives = listOf(
                "To understand the core concepts and mindset of entrepreneurship.",
                "To identify entrepreneurial traits, motivation, and opportunity recognition.",
                "To study institutional support agencies (DIC, MSME, KVIC, Mudra).",
                "To formulate feasible business plans and project reports."
            ),
            courseOutcomes = listOf(
                "COS1: Explain entrepreneurial functions and competency models.",
                "COS2: Assess institutional financial support and government schemes.",
                "COS3: Develop Business Model Canvas and project feasibility reports."
            ),
            booksPrescribed = listOf(
                "Entrepreneurship Development by S.S. Khanka, S. Chand & Co.",
                "Dynamics of Entrepreneurial Development by Vasant Desai, Himalaya Publishing.",
                "Entrepreneurial Development by Poornima M. Charantimath, Pearson."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Unit I: Concept of Entrepreneurship & Entrepreneur",
                    description = "Definitions, traits, functions, types of entrepreneurs, and role in economic development.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd114_u1_t1",
                            title = "Entrepreneurship Concept, Definitions & Functions",
                            unitNumber = 1,
                            overview = "Entrepreneurship is the dynamic process of creating incremental wealth and innovation by assuming financial, psychological, and social risks.",
                            keyPoints = listOf(
                                "Definitions by Joseph Schumpeter (Innovation theory) and Peter Drucker (Opportunity exploitation).",
                                "Functions: Risk-bearing, innovation, decision making, management, and resource coordination.",
                                "Traits: Vision, calculated risk taking, persistence, self-confidence, leadership."
                            ),
                            importantTerms = mapOf(
                                "Entrepreneur" to "An individual who initiates, organizes, and manages a business venture while assuming risks for profit.",
                                "Intrapreneur" to "An innovative corporate employee who acts as an entrepreneur within an existing organization."
                            )
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Unit II: Entrepreneurial Motivation & Competencies",
                    description = "Theories of motivation (McClelland's Need for Achievement), creativity, problem solving, and barriers.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd114_u2_t1",
                            title = "Motivational Factors & McClelland's Achievement Theory",
                            unitNumber = 2,
                            overview = "Understanding the internal and external drivers that push individuals into launching textile boutiques, design studios, and export units.",
                            keyPoints = listOf(
                                "David McClelland's Theory: Need for Achievement (n-Ach), Need for Power (n-Pow), Need for Affiliation (n-Aff).",
                                "Push Factors (unemployment, necessity) vs Pull Factors (independence, innovation, wealth).",
                                "Overcoming internal and external barriers in fashion startups."
                            ),
                            importantTerms = mapOf(
                                "n-Ach" to "High need for personal achievement and setting challenging goals."
                            )
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 3,
                    title = "Unit III: Institutional Support Ecosystem",
                    description = "Role of DIC, MSME Development Institutes, KVIC, SIDBI, NSIC, Mudra Bank, and Startup India.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd114_u3_t1",
                            title = "Government Support Agencies & Financial Schemes",
                            unitNumber = 3,
                            overview = "Comprehensive review of state and central bodies offering subsidies, industrial land, skill training, and credit guarantees to MSMEs.",
                            keyPoints = listOf(
                                "DIC (District Industries Centre): Single-window assistance at district level.",
                                "MSME Ministry: Technology upgradation subsidies and cluster development.",
                                "Mudra Yojana: Shishu (up to ₹50k), Kishore (₹50k-₹5L), Tarun (₹5L-₹10L) micro-loans.",
                                "SIDBI & NSIC: Raw material assistance and machinery leasing."
                            ),
                            importantTerms = mapOf(
                                "DIC" to "District Industries Centre providing grassroots entrepreneurial facilitation.",
                                "MSME" to "Micro, Small and Medium Enterprises defined under MSMED Act."
                            )
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 4,
                    title = "Unit IV: Project Formulation & Business Model Canvas",
                    description = "Project identification, Business Model Canvas (BMC), techno-economic feasibility, and project report preparation.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd114_u4_t1",
                            title = "Business Model Canvas (BMC) & Project Feasibility",
                            unitNumber = 4,
                            overview = "Structuring a 9-block Business Model Canvas covering Value Proposition, Customer Segments, Revenue Streams, and Cost Structure for textile enterprises.",
                            keyPoints = listOf(
                                "9 Blocks of BMC: Key Partners, Activities, Resources, Value Proposition, Customer Relationships, Channels, Segments, Cost Structure, Revenue Streams.",
                                "Feasibility Analysis: Technical, Market, Financial, and Management feasibility.",
                                "Components of a Bankable Detailed Project Report (DPR)."
                            ),
                            importantTerms = mapOf(
                                "Value Proposition" to "The unique benefit or solution a business offers to its target customers.",
                                "DPR" to "Detailed Project Report required by banks and funding bodies."
                            )
                        )
                    )
                )
            )
        ),

        // 5. CS-BVTD111: Computer Application-I (Minor - Theory + Practical)
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
            overview = "Computer fundamentals, operating systems, and productivity suites (MS Word, MS Excel, MS PowerPoint) applied to textile documentation and fashion cost sheets.",
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Computer Fundamentals & Operating Systems",
                    description = "Hardware architecture, CPU, memory, storage devices, and Windows OS management.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "MS Word for Fashion Documentation",
                    description = "Formatting tech packs, tables, headers/footers, and mail merge.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 3,
                    title = "MS Excel for Apparel Costing & Spreadsheets",
                    description = "Formulas, functions, costing sheets, and inventory data charts.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 4,
                    title = "MS PowerPoint for Design Presentations",
                    description = "Slide masters, multimedia integration, and fashion mood board pitching.",
                    isOfficialUnit = true
                )
            )
        ),

        // 6. BCSV-1129: Communication Skills in English-I (Ability Enhancement - Theory)
        Subject(
            code = "BCSV-1129",
            name = "Communication Skills in English-I",
            semesterNumber = 1,
            category = CourseCategory.ABILITY_ENHANCEMENT,
            type = SubjectType.THEORY,
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
            overview = "Core English communication skills, reading comprehension, grammar mechanics, technical vocabulary, and practical spoken English lab.",
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Reading Comprehension & Critical Analysis",
                    description = "Skimming, scanning, textual analysis, and professional vocabulary building.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Applied Grammar & Sentence Mechanics",
                    description = "Tenses, active/passive voice, subject-verb agreement, and technical writing.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 3,
                    title = "Business & Professional Correspondence",
                    description = "Formal letters, emails, memorandums, and inquiry drafting.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 4,
                    title = "Oral Communication & Spoken English Lab",
                    description = "Pronunciation, phonetics, self-introduction, and viva presentations.",
                    isOfficialUnit = true
                )
            )
        ),

        // 7. BHPB-1101 / BPBI-1102 / BPHC-1104: Punjabi / Punjab History & Culture (Ability Enhancement - Theory)
        Subject(
            code = "BHPB-1101",
            name = "Punjabi (Compulsory) / Punjab History & Culture",
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
            overview = "Language structure, regional literary texts, cultural history of Punjab, and traditional folk crafts like Phulkari and Durrie weaving.",
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Punjabi Literature & Language Structure",
                    description = "Selected literary texts, grammatical rules, and linguistic comprehension.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Punjab Cultural Heritage & Folk Textile Arts",
                    description = "Traditional Phulkari embroidery (Chope, Subhar, Bagh), handloom weaving, and folk crafts.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bhpb1101_u2_t1",
                            title = "Traditional Punjabi Phulkari & Folk Craft Traditions",
                            unitNumber = 2,
                            overview = "Study of Phulkari embroidery worked on coarse khaddar fabric using untwisted silk floss (pat) in counted darn stitch.",
                            keyPoints = listOf(
                                "Geometrical darn stitch worked exclusively from the reverse side.",
                                "Varieties: Bagh (entire ground covered), Chope (red border gift for bride), Subhar.",
                                "GI (Geographical Indication) status of authentic Punjab Phulkari."
                            ),
                            importantTerms = mapOf(
                                "Pat" to "Untwisted glossy silk embroidery thread used in Phulkari.",
                                "Khaddar" to "Hand-spun, hand-woven coarse cotton ground fabric."
                            )
                        )
                    )
                )
            )
        ),

        // 8. ZDA111: Drug Abuse: Problems, Management and Prevention (Value Added - Theory)
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
            overview = "Value-added curriculum on public health awareness, prevention of substance abuse, psychological management, and social rehabilitation.",
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Substance Abuse: Nature, Impact & Prevention",
                    description = "Biological mechanisms, social consequences, counseling, and youth prevention programs.",
                    isOfficialUnit = true
                )
            )
        )
    )

    // =========================================================================
    // COMPLETE OFFICIAL SEMESTER 2 SUBJECTS (8 Subjects: Theory & Practical)
    // =========================================================================
    private val semester2Subjects = listOf(
        // 1. BVTD 121: Introduction to Fashion (Major - Theory)
        Subject(
            code = "BVTD121",
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
            overview = "Comprehensive study of the fashion world: terminologies, the 5-stage fashion cycle, adoption theories, international fashion capitals, legendary global designers, and the Indian fashion industry.",
            timeDurationHours = 3,
            mediumOfExam = "English, Hindi, Punjabi",
            instructionsForPaperSetters = "The question paper consists of five sections: Section A (compulsory short questions) and Sections B, C, D, E covering Units I to IV. Students must attempt one question from each unit.",
            courseObjectives = listOf(
                "To understand fashion terminology, concepts, and the fashion life cycle.",
                "To analyze theories of fashion adoption and consumer leadership.",
                "To study world fashion capitals and pioneering international designers.",
                "To explore Indian fashion heritage, textiles, and contemporary Indian designers."
            ),
            learningObjectives = listOf(
                "Define essential fashion vocabulary (Fad, Trend, Classic, Silhouette, Haute Couture, Prêt-à-Porter).",
                "Analyze the stages of the 5-phase Fashion Cycle.",
                "Evaluate Trickle-Down, Trickle-Up, and Trickle-Across diffusion models.",
                "Study iconic global and Indian couturiers."
            ),
            courseOutcomes = listOf(
                "COS1: Interpret the fashion cycle and forecast trend movements.",
                "COS2: Critique fashion adoption theories and target consumer demographics.",
                "COS3: Evaluate global design aesthetics and traditional Indian craft fusion."
            ),
            booksPrescribed = listOf(
                "Fashion: From Concept to Consumer by Gini Stephens Frings, Pearson.",
                "Inside the Fashion Business by Jeannette Jarnow, Fairchild Books.",
                "The Dynamics of Fashion by Elaine Stone, Fairchild Publications."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Unit I: Fashion Terminology & The Fashion Life Cycle",
                    description = "Core terms (Style, Fashion, Design, Fad, Classic, Trend, Silhouette, Haute Couture, Prêt-à-Porter) and the 5 stages of the Fashion Cycle.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd121_u1_t1",
                            title = "Fashion Terminology & The 5-Stage Fashion Cycle",
                            unitNumber = 1,
                            overview = "Fashion is the style currently accepted by a majority. The cycle tracks introduction, rise, peak, decline, and obsolescence.",
                            keyPoints = listOf(
                                "Introduction Stage: New designs shown at runway/haute couture, high prices, limited volume.",
                                "Rise Stage: Mass manufacturers adapt style, featured in media, increasing sales.",
                                "Peak / Culmination: Style is at height of popularity, mass-market retail availability.",
                                "Decline Stage: Market saturation, price discounting and clearance sales.",
                                "Obsolescence: End of lifecycle; consumers have moved to the next fashion cycle."
                            ),
                            importantTerms = mapOf(
                                "Fad" to "A short-lived fashion craze that enters quickly, peaks rapidly, and vanishes.",
                                "Classic" to "A timeless style that remains in fashion over decades (e.g., Trench Coat, Little Black Dress, Blue Jeans).",
                                "Haute Couture" to "Custom-fitted, high-end high fashion hand-crafted in Paris according to strict legal standards.",
                                "Prêt-à-Porter" to "High-quality factory-produced ready-to-wear fashion collections."
                            ),
                            visualExplanation = "Bell curve chart depicting the 5 stages of the Fashion Life Cycle with price vs volume trajectories.",
                            industrialRelevance = "Crucial for retail buying, inventory clearance timing, and collection launching schedules.",
                            quickRevisionSummary = "Cycle = Introduction -> Rise -> Peak -> Decline -> Obsolescence."
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Unit II: Theories of Fashion Adoption & Consumer Behavior",
                    description = "Trickle-Down (Downward Flow), Trickle-Up (Bottom-Up / Upward Flow), and Trickle-Across (Mass Market / Horizontal Flow) theories.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd121_u2_t1",
                            title = "Fashion Adoption Theories & Consumer Groups",
                            unitNumber = 2,
                            overview = "Diffusion theories explain how fashion movements spread across socio-economic classes and consumer demographics.",
                            keyPoints = listOf(
                                "Trickle-Down (Veblen/Simmel): Starts with wealthy elites; copied by lower classes.",
                                "Trickle-Up (Field): Originates on streets or youth subcultures and moves up to luxury runways (e.g., Denim, Punk, Grunge, Streetwear).",
                                "Trickle-Across (King): Simultaneous availability across all price points via mass media and rapid manufacturing.",
                                "Consumer Groups: Fashion Innovators (early adopters) -> Opinion Leaders -> Followers -> Laggards."
                            ),
                            importantTerms = mapOf(
                                "Trickle-Up Theory" to "Fashion diffusion originating from grassroots youth subcultures and adopted by high-fashion couturiers.",
                                "Fashion Innovators" to "Consumers who buy and wear new styles earliest in the introduction phase."
                            )
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 3,
                    title = "Unit III: World Fashion Capitals & Legendary Designers",
                    description = "The Big 4 Fashion Capitals (Paris, Milan, London, New York) and iconic couturiers (Chanel, Dior, Saint Laurent, McQueen).",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd121_u3_t1",
                            title = "Global Fashion Capitals & Master Designers",
                            unitNumber = 3,
                            overview = "Paris (Haute Couture & elegance), Milan (Craftsmanship & luxury leather/textiles), London (Avant-garde & tailoring), New York (Sportswear & commercial readiness).",
                            keyPoints = listOf(
                                "Coco Chanel: The Little Black Dress, tweed suits, costume jewelry, comfortable relaxed silhouettes.",
                                "Christian Dior: 1947 'New Look' with cinched waist and full voluptuous skirts.",
                                "Yves Saint Laurent: Le Smoking tuxedo suit for women, safari jackets, prêt-à-porter pioneer.",
                                "Alexander McQueen: Dramatic tailoring, theatrical runway shows, and technical innovation."
                            ),
                            importantTerms = mapOf(
                                "New Look (1947)" to "Christian Dior's revolutionary silhouette with nipped-in waist, soft shoulders, and voluminous pleated skirts.",
                                "Le Smoking" to "The first tuxedo suit tailored for women by Yves Saint Laurent in 1966."
                            )
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 4,
                    title = "Unit IV: Indian Fashion Industry, Heritage & Designers",
                    description = "Traditional Indian craft heritage, fashion councils (FDCI), and celebrated Indian designers (Ritu Kumar, Sabyasachi, Manish Malhotra, Anita Dongre).",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd121_u4_t1",
                            title = "Indian Fashion Evolution & Renowned Designers",
                            unitNumber = 4,
                            overview = "The Indian fashion landscape combines rich handloom heritage (Zardozi, Banarasi, Chanderi, Bandhani) with contemporary global silhouettes.",
                            keyPoints = listOf(
                                "Ritu Kumar: Pioneer in reviving Indian hand-block printing, Zardozi, and royal textiles.",
                                "Sabyasachi Mukherjee: Champion of heritage bridal couture, vintage aesthetics, and artisanal weavers.",
                                "Manish Malhotra: Glamour, Bollywood costume styling, and contemporary ethnic diffusion.",
                                "Anita Dongre: Sustainable luxury, Grassroot artisan initiative, and global royal dressing.",
                                "FDCI (Fashion Design Council of India): Organizes India Fashion Week and promotes designer mentorship."
                            ),
                            importantTerms = mapOf(
                                "FDCI" to "Fashion Design Council of India, the apex fashion governing body in India.",
                                "Zardozi" to "Opulent gold/silver metallic wire embroidery traditionally favored by royal Mughal ateliers."
                            )
                        )
                    )
                )
            )
        ),

        // 2. BVTD 122: Garment sewing(practical) - Major - Practical
        Subject(
            code = "BVTD122",
            name = "Garment sewing(practical)",
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
            overview = "Drafting foundation bodice blocks, dart manipulation techniques, sleeve/collar pattern drafting, pocket assemblies, and complete adult garment construction.",
            practicals = sem2PracticalsBvtd122,
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Bodice Block Drafting & Dart Relocation",
                    description = "Adult foundation sloper drafting, pivotal transfer, slash-and-spread method.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Advanced Component Construction",
                    description = "Tailored collars with stands, flap pockets, bound welt pockets, sleeve styles.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 3,
                    title = "Full Garment Sewing Project (Kurti / Tunic / Dress)",
                    description = "End-to-end cutting, seam finishing, neck placket, sleeve setting, and hem finishes.",
                    isOfficialUnit = true
                )
            )
        ),

        // 3. BVTD 123: Design foundation and basics of textiles – II(Practical) - Major - Practical
        Subject(
            code = "BVTD123",
            name = "Design foundation and basics of textiles – II(Practical)",
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
            overview = "Woven fabric weave analysis (Plain, Twill, Satin, Honeycomb), drafting/lifting plans on point paper, Tie & Dye, Shibori, Batik wax resist, block printing, and textile physical testing (GSM, Yarn Count, EPI/PPI).",
            practicals = sem2PracticalsBvtd123,
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Weave Analysis & Point Paper Graphing",
                    description = "Plain 1/1, Twills (2/1, 2/2, 3/1), Satin, Sateen, and drafting/lifting plans.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Resist Dyeing & Surface Ornamentation Lab",
                    description = "Tie & Dye (Bandhani), Shibori folding, Batik wax crackle, and block printing.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 3,
                    title = "Textile Testing & Quality Analysis Lab",
                    description = "Beesley's yarn count, Fabric GSM cutter, and pick glass thread density analysis.",
                    isOfficialUnit = true
                )
            )
        ),

        // 4. BVTD 124: Enterprise Planning (Minor - Theory)
        Subject(
            code = "BVTD124",
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
            overview = "Enterprise planning fundamentals, market feasibility, plant layout & machinery selection in garment factories, financial management & costing, and industrial legal compliance.",
            timeDurationHours = 3,
            mediumOfExam = "English, Hindi, Punjabi",
            instructionsForPaperSetters = "The question paper consists of five sections: Section A (compulsory short questions) and Sections B, C, D, E covering Units I to IV. Students attempt one question from each unit.",
            courseObjectives = listOf(
                "To understand enterprise planning concepts and market opportunity analysis.",
                "To plan garment factory plant layouts, line balancing, and machinery sourcing.",
                "To master financial planning, working capital estimation, and garment costing methods.",
                "To study legal, labor, and environmental compliance frameworks for MSMEs."
            ),
            courseOutcomes = listOf(
                "COS1: Formulate enterprise strategic plans and SWOT/market assessments.",
                "COS2: Design industrial garment plant layouts and equipment workflows.",
                "COS3: Calculate apparel product cost sheets (CM/FOB) and break-even points.",
                "COS4: Apply Factory Act, labor welfare, and MSME regulatory requirements."
            ),
            booksPrescribed = listOf(
                "Apparel Manufacturing Technology by T. Karthik & P. Ganesan, CRC Press.",
                "Garment Manufacturing Technology by E. Nayak & R. Padhye, Woodhead Publishing.",
                "Small Scale Industries and Entrepreneurship by Vasant Desai, Himalaya Publishing."
            ),
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Unit I: Enterprise Planning & Opportunity Assessment",
                    description = "Strategic vision, mission, environmental scanning, market analysis, SWOT and PESTEL for apparel ventures.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd124_u1_t1",
                            title = "Enterprise Planning Fundamentals & SWOT Analysis",
                            unitNumber = 1,
                            overview = "Enterprise planning charts the long-term vision, operational roadmap, and market positioning for textile startups.",
                            keyPoints = listOf(
                                "Components of Enterprise Plan: Executive summary, Market analysis, Operations, Financial projections.",
                                "SWOT Analysis: Internal Strengths & Weaknesses vs External Opportunities & Threats in fashion markets.",
                                "Identifying apparel market gaps (e.g., sustainable fashion, size-inclusive wear, technical textiles)."
                            ),
                            importantTerms = mapOf(
                                "SWOT Analysis" to "Strategic tool evaluating Strengths, Weaknesses, Opportunities, and Threats.",
                                "Market Segmentation" to "Dividing target consumer market into distinct groups with shared needs."
                            )
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Unit II: Plant Layout, Machinery Selection & Workflow",
                    description = "Plant location factors, assembly line balancing, machinery selection (SNLS, overlock, flatlock, cutting), and material handling.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd124_u2_t1",
                            title = "Garment Factory Layout & Production Machinery",
                            unitNumber = 2,
                            overview = "Optimizing physical layout (Product vs Process layout) to minimize material handling time and maximize operator efficiency.",
                            keyPoints = listOf(
                                "Plant Location Factors: Proximity to raw material textile hubs (Ludhiana/Surat), skilled labor, transport ports.",
                                "Essential Machinery: Straight knife cutting machines, Single Needle Lockstitch (SNLS), 4/5-thread Overlock, Flatlock, fusing presses.",
                                "Line Balancing: Distributing SAM (Standard Allowed Minutes) equally across workstations to eliminate bottlenecks."
                            ),
                            importantTerms = mapOf(
                                "Line Balancing" to "Evenly distributing operations along an assembly line to prevent bottlenecks.",
                                "SAM" to "Standard Allowed Minutes required by a qualified operator to complete a specific sewing operation."
                            )
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 3,
                    title = "Unit III: Financial Planning & Garment Costing",
                    description = "Fixed vs variable costs, working capital management, Bill of Materials (BOM), CM and FOB costing, Break-Even Analysis.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd124_u3_t1",
                            title = "Apparel Costing (CM / FOB) & Break-Even Analysis",
                            unitNumber = 3,
                            overview = "Detailed calculation of garment export pricing including fabric consumption, trims, CMT (Cut-Make-Trim), overheads, and profit margins.",
                            keyPoints = listOf(
                                "Cost Components: Direct Material (Fabric 60-70% of total cost, trims), Direct Labor, Factory Overheads.",
                                "CM (Cut and Make) vs CMT (Cut, Make and Trim) vs FOB (Free on Board - full package).",
                                "Break-Even Point (BEP): The sales volume at which total revenue equals total costs (Fixed Costs / Contribution Margin per unit)."
                            ),
                            importantTerms = mapOf(
                                "FOB Costing" to "Free on Board price where the manufacturer covers all costs up to loading onto shipping vessel.",
                                "Break-Even Point" to "The point at which a business incurs zero profit and zero loss."
                            )
                        )
                    )
                ),
                SubjectUnit(
                    unitNumber = 4,
                    title = "Unit IV: Legal Framework, Compliance & MSME Registration",
                    description = "The Factories Act 1948, labor safety, environmental effluent treatment (ETP), Udyam registration, and GST compliance.",
                    isOfficialUnit = true,
                    topics = listOf(
                        TopicContent(
                            id = "bvtd124_u4_t1",
                            title = "Industrial Compliance, Labor Laws & MSME Formalities",
                            unitNumber = 4,
                            overview = "Navigating regulatory standards required to operate a lawful, socially compliant apparel manufacturing enterprise.",
                            keyPoints = listOf(
                                "The Factories Act 1948: Health, safety, working hours, ventilation, and welfare of factory workers.",
                                "Environmental Compliance: Zero Liquid Discharge (ZLD) and Effluent Treatment Plants (ETP) for dyeing units.",
                                "Udyam Portal: Government online registration for MSME certification and priority bank lending.",
                                "Ethical Audits: WRAP (Worldwide Responsible Accredited Production) and Sedex compliance for export houses."
                            ),
                            importantTerms = mapOf(
                                "ETP" to "Effluent Treatment Plant for neutralizing chemical and dye wastewater.",
                                "Udyam" to "Official Indian government MSME self-declaration registration portal."
                            )
                        )
                    )
                )
            )
        ),

        // 5. CS-BVTD121: Computer Applications-II(Practical) - Minor - Practical
        Subject(
            code = "CS-BVTD121",
            name = "Computer Applications-II(Practical)",
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
            overview = "Advanced Fashion CAD lab covering CorelDRAW for vector flats and spec sheets, Adobe Photoshop for digital seamless print repeats and mood boards, and complete digital tech pack creation.",
            practicals = sem2PracticalsCsBvtd121,
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Vector Fashion Design in CorelDRAW",
                    description = "9-head croquis, flat sketches of garments, stitch rendering, and spec sheets.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Digital Surface & Print Design in Adobe Photoshop",
                    description = "Seamless textile repeat generation (Straight, Half-drop), motif extraction, and mood boards.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 3,
                    title = "Digital Tech Pack & Export Documentation Project",
                    description = "Comprehensive industry tech pack with flats, measurements, BOM, and colorways.",
                    isOfficialUnit = true
                )
            )
        ),

        // 6. BCSV-1229: Communication Skills in English-II (Ability Enhancement - Theory)
        Subject(
            code = "BCSV-1229",
            name = "Communication Skills in English-II",
            semesterNumber = 2,
            category = CourseCategory.ABILITY_ENHANCEMENT,
            type = SubjectType.THEORY,
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
            overview = "Advanced professional communication: Resume / CV writing, job applications, presentation techniques, interview skills, and professional spoken English.",
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Professional Writing & Resume Preparation",
                    description = "Chronological/Functional CVs, cover letters, job applications, and technical reports.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Public Speaking & Design Portfolio Pitching",
                    description = "Presentation structuring, visual aids, non-verbal communication, and body language.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 3,
                    title = "Interview Skills & Group Discussions",
                    description = "Facing HR & technical panels, answering situational questions, and GD strategies.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 4,
                    title = "Advanced Language Lab & Spoken Fluency",
                    description = "Mock interviews, telephone etiquette, and interactive debate sessions.",
                    isOfficialUnit = true
                )
            )
        ),

        // 7. BHPB-1201 / BPBI-1202 / BPHC-1204: Punjabi / Punjab History & Culture (Ability Enhancement - Theory)
        Subject(
            code = "BHPB-1201",
            name = "Punjabi (Compulsory) / Punjab History & Culture",
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
            overview = "Punjabi poetry, cultural narratives, historical developments in Punjab, and artisan handloom traditions.",
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Punjabi Literary Forms & Prose",
                    description = "Selected literary texts, essays, and advanced grammatical comprehension.",
                    isOfficialUnit = true
                ),
                SubjectUnit(
                    unitNumber = 2,
                    title = "Punjab Heritage & Social History",
                    description = "Historical survey of cultural movements, trade routes, and craft guilds.",
                    isOfficialUnit = true
                )
            )
        ),

        // 8. ZDA121: Drug Abuse: Problems, Management and Prevention (Value Added - Theory)
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
            overview = "Value-added curriculum on youth prevention programs, social rehabilitation networks, and community counseling.",
            units = listOf(
                SubjectUnit(
                    unitNumber = 1,
                    title = "Prevention Strategies & Rehabilitation Systems",
                    description = "Role of educational institutions, peer support groups, and family counseling.",
                    isOfficialUnit = true
                )
            )
        )
    )

    // =========================================================================
    // SEMESTER DEFINITIONS (Semesters 1 & 2 Active; Semesters 3-6 Coming Soon)
    // =========================================================================
    val semesters: List<Semester> = listOf(
        Semester(
            number = 1,
            title = "Semester I",
            yearNumber = 1,
            totalCredits = semester1Subjects.sumOf { it.totalCredits },
            totalMarks = semester1Subjects.sumOf { it.totalMarks },
            totalHoursPerWeek = semester1Subjects.sumOf { it.hoursPerWeek },
            subjects = semester1Subjects
        ),
        Semester(
            number = 2,
            title = "Semester II",
            yearNumber = 1,
            totalCredits = semester2Subjects.sumOf { it.totalCredits },
            totalMarks = semester2Subjects.sumOf { it.totalMarks },
            totalHoursPerWeek = semester2Subjects.sumOf { it.hoursPerWeek },
            subjects = semester2Subjects
        )
    )

    val academicYears: List<AcademicYear> = listOf(
        AcademicYear(
            yearNumber = 1,
            title = "1st Year (Certificate / Diploma Stage)",
            semesters = semesters,
            isSyllabusAvailable = true
        ),
        AcademicYear(
            yearNumber = 2,
            title = "2nd Year (Advanced Diploma Stage)",
            semesters = listOf(
                Semester(number = 3, title = "Semester III", yearNumber = 2, totalCredits = 0, totalMarks = 0, totalHoursPerWeek = 0, subjects = emptyList()),
                Semester(number = 4, title = "Semester IV", yearNumber = 2, totalCredits = 0, totalMarks = 0, totalHoursPerWeek = 0, subjects = emptyList())
            ),
            isSyllabusAvailable = false
        ),
        AcademicYear(
            yearNumber = 3,
            title = "3rd Year (B.Voc Degree Stage)",
            semesters = listOf(
                Semester(number = 5, title = "Semester V", yearNumber = 3, totalCredits = 0, totalMarks = 0, totalHoursPerWeek = 0, subjects = emptyList()),
                Semester(number = 6, title = "Semester VI", yearNumber = 3, totalCredits = 0, totalMarks = 0, totalHoursPerWeek = 0, subjects = emptyList())
            ),
            isSyllabusAvailable = false
        )
    )

    val careerRoles = listOf(
        CareerRole(
            title = "Apparel Production & Quality Controller",
            sector = "Export Houses & Garment Mills",
            description = "Supervises industrial assembly lines, checks AQL (Acceptable Quality Limit), monitors SPI, seam strength, and manages operator floor workflows in textile hubs like Ludhiana and Tirupur.",
            keySkills = listOf("Seam Engineering", "AQL Quality Audits", "Line Balancing", "Production Costing"),
            standardTools = listOf("Gerber Accumark", "Fast React Production Planner", "Spectrophotometer"),
            industryScope = "Massive demand in Punjab garment clusters, Delhi-NCR export zones, and international apparel manufacturing."
        ),
        CareerRole(
            title = "Fashion Boutique & Apparel Entrepreneur",
            sector = "Independent Studio & Retail Brand",
            description = "Launches proprietary designer boutique, manages custom couture, sources artisanal fabrics, coordinates pattern masters, and leverages MSME/Mudra financing schemes.",
            keySkills = listOf("Business Model Canvas", "Couture Sewing Techniques", "Client Consultation", "Working Capital Management"),
            standardTools = listOf("Shopify POS", "Instagram Business Suite", "Tally ERP / QuickBooks"),
            industryScope = "High-growth self-employment avenue across urban Punjab, Tier-1/2 Indian cities, and global NRI fashion markets."
        ),
        CareerRole(
            title = "Textile Surface & Print Designer",
            sector = "Textile Mills & Design Studios",
            description = "Creates digital and rotary print collections, weaves jacquard motifs, and coordinates color palettes for apparel and home furnishing exports.",
            keySkills = listOf("Color Theory & Harmonization", "Pattern Repeats", "Weave Analysis", "Trend Forecasting"),
            standardTools = listOf("Adobe Photoshop", "NedGraphics", "CorelDRAW", "Pantone Matcher"),
            industryScope = "High requirement across domestic mills, home textile exporters in Panipat, and global design houses."
        )
    )

    val sampleResources = listOf(
        CourseResource(
            id = "res_1",
            title = "BVTD113 Sewing Techniques Practical Workbook",
            subjectCode = "BVTD113",
            category = "Manual",
            description = "Official laboratory workbook covering machine care, seam samples, plackets, pockets, sleeves, and collars with technical diagrams.",
            downloadSize = "4.8 MB",
            format = "PDF"
        ),
        CourseResource(
            id = "res_2",
            title = "BVTD114 Introduction to Entrepreneurship Study Guide",
            subjectCode = "BVTD114",
            category = "Notes",
            description = "Units I to IV study notes including definitions, functions, traits, DIC/MSME/Mudra institutional support, and BMC framework.",
            downloadSize = "3.2 MB",
            format = "PDF"
        ),
        CourseResource(
            id = "res_3",
            title = "BVTD121 Fashion Cycle & Adoption Theories Chart",
            subjectCode = "BVTD121",
            category = "Diagram",
            description = "Visual decision tree and 5-stage lifecycle bell curve for fashion forecasting and merchandising.",
            downloadSize = "1.8 MB",
            format = "PNG"
        ),
        CourseResource(
            id = "res_4",
            title = "BVTD124 Enterprise Planning Costing & Layout Guide",
            subjectCode = "BVTD124",
            category = "Notes",
            description = "Plant layout schematics, line balancing math, CM/FOB costing equations, and break-even calculations.",
            downloadSize = "2.6 MB",
            format = "PDF"
        )
    )

    fun getAllSubjects(): List<Subject> = semesters.flatMap { it.subjects }

    fun getSubjectByCode(code: String): Subject? {
        val cleanCode = code.replace(" ", "").replace("-", "").uppercase()
        return getAllSubjects().find { 
            it.code.replace(" ", "").replace("-", "").uppercase() == cleanCode 
        }
    }

    fun getAllPracticals(): List<PracticalActivity> {
        return getAllSubjects().flatMap { it.practicals }
    }
}
