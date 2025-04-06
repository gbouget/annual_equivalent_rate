import java.io.ByteArrayOutputStream
import java.io.File

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    application
    base
}

group = "dev.gbouget"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass.set("dev.gbouget.interest_tools.InterestToolsApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Utilisation du répertoire utilisateur avec ~
val userHome = System.getProperty("user.home")
val latexmkPath = File(userHome, "AppData/Local/Programs/MiKTeX/miktex/bin/x64/latexmk.exe")
    .absolutePath
    .replace("\\", "/")

// Tâche pour compiler le fichier LaTeX en PDF avec latexmk
tasks.register<Exec>("compileLaTeX") {
    description = "Compile le fichier LaTeX en PDF avec latexmk"
    group = "build"
    
    // Vérifier que le fichier existe avant compilation
    doFirst {
        file("src/main/resources/latex").mkdirs()
        if (!file("src/main/resources/latex/main.tex").exists()) {
            throw GradleException("Le fichier main.tex n'existe pas dans src/main/resources/latex/")
        }
        
        if (!File(latexmkPath).exists()) {
            throw GradleException("latexmk n'a pas été trouvé à l'emplacement: $latexmkPath")
        }
    }
    
    // Définir le répertoire de travail
    workingDir = file("src/main/resources/latex")
    
    // Utiliser le chemin absolu vers latexmk
    commandLine(latexmkPath, "-pdf", "-interaction=nonstopmode", "main.tex")
    
    // Capture et affichage de la sortie
    val stdout = ByteArrayOutputStream()
    val stderr = ByteArrayOutputStream()
    
    standardOutput = stdout
    errorOutput = stderr
    
    doLast {
        println("Sortie standard:")
        println(stdout.toString())
        
        println("Sortie d'erreur:")
        println(stderr.toString())
    }
}

// Tâche pour nettoyer les fichiers temporaires LaTeX
tasks.register<Delete>("cleanLaTeX") {
    description = "Nettoie les fichiers temporaires de LaTeX"
    group = "build"
    
    delete(
        fileTree("src/main/resources/latex") {
            include("*.aux")        // Fichiers auxiliaires
            include("*.log")        // Fichiers log
            include("*.out")        // Fichiers de sortie
            include("*.toc")        // Table des matières
            include("*.lof")        // Liste des figures
            include("*.lot")        // Liste des tableaux
            include("*.fls")        // Fichiers de liste générés par latexmk
            include("*.fdb_latexmk")// Base de données latexmk
            include("*.bbl")        // Fichiers bibliographiques
            include("*.bcf")        // Fichiers de contrôle bibliographique
            include("*.blg")        // Fichiers log bibliographiques
            include("*.run.xml")    // Fichiers XML générés
            include("*.synctex.gz") // Fichiers de synchronisation SyncTeX
            include("*.dvi")        // Fichiers DVI
            include("*.xdv")        // Fichiers XDV (XeTeX)
            include("*-blx.bib")    // Fichiers temporaires de biblatex
            include("*.nav")        // Fichiers de navigation (beamer)
            include("*.snm")        // Fichiers de notes (beamer)
            include("*.vrb")        // Fichiers verbatim (beamer)
            include("_minted-*/**") // Dossiers créés par le package minted
            include("*.pyg")        // Fichiers pygments
            include("*.listing")    // Fichiers listing
            include("*.idx")        // Fichiers d'index
            include("*.ilg")        // Fichiers log d'index
            include("*.ind")        // Fichiers d'index compilés
            include("*.glo")        // Fichiers de glossaire
            include("*.gls")        // Fichiers de glossaire compilés
            include("*.glg")        // Fichiers log de glossaire
            include("*.acn")        // Fichiers d'acronymes
            include("*.acr")        // Fichiers d'acronymes compilés
            include("*.alg")        // Fichiers log d'acronymes
            include("*.ist")        // Fichiers de style d'index
            
            // Ne pas supprimer les fichiers source et PDF
            exclude("*.tex")
            exclude("*.pdf")
            exclude("*.cls")
            exclude("*.sty")
            exclude("*.bib")
            exclude("assets/**")
        }
    )
}

// Tâche pour nettoyer tous les fichiers, y compris les PDF
tasks.register<Delete>("cleanLaTeXAll") {
    description = "Nettoie tous les fichiers générés par LaTeX, y compris les PDF"
    group = "build"
    
    delete(
        fileTree("src/main/resources/latex") {
            include("*.aux", "*.log", "*.out", "*.toc", "*.lof", "*.lot", "*.fls", "*.fdb_latexmk")
            include("*.bbl", "*.bcf", "*.blg", "*.run.xml", "*.synctex.gz", "*.dvi", "*.xdv")
            include("*-blx.bib", "*.nav", "*.snm", "*.vrb", "_minted-*/**", "*.pyg", "*.listing")
            include("*.idx", "*.ilg", "*.ind", "*.glo", "*.gls", "*.glg", "*.acn", "*.acr", "*.alg", "*.ist")
            include("*.pdf")  // Inclut aussi les PDF
            
            // Ne pas supprimer les fichiers source
            exclude("*.tex", "*.cls", "*.sty", "*.bib", "assets/**")
        }
    )
}

// Option pour ajouter un nettoyage avant compilation
tasks.register("buildLaTeXClean") {
    description = "Nettoie les fichiers temporaires puis compile le document LaTeX"
    group = "build"
    
    dependsOn("cleanLaTeX", "compileLaTeX")
    tasks.findByName("compileLaTeX")?.mustRunAfter("cleanLaTeX")
}

// Ajouter la compilation LaTeX à la tâche build
tasks.named("build") {
    dependsOn("compileLaTeX")
}

// Ajouter le nettoyage LaTeX à la tâche clean
tasks.named("clean") {
    dependsOn("cleanLaTeX")
}