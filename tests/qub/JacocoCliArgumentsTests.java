package qub;

public interface JacocoCliArgumentsTests
{
    static <T extends JacocoCliArguments<T>> void test(TestRunner runner, Function1<Test,T> creator)
    {
        runner.testGroup(JacocoCliArguments.class, () ->
        {
            runner.testGroup("addArguments(String...)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addArguments((String[])null),
                        new PreConditionFailure("arguments cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with no arguments", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    final T addArgumentsResult = jacocoArguments.addArguments();
                    test.assertSame(jacocoArguments, addArgumentsResult);
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with one argument", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    final T addArgumentsResult = jacocoArguments.addArguments("hello");
                    test.assertSame(jacocoArguments, addArgumentsResult);
                    test.assertEqual(Iterable.create("hello"), jacocoArguments.getArguments());
                });

                runner.test("with two arguments", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    final T addArgumentsResult = jacocoArguments.addArguments("hello", "there");
                    test.assertSame(jacocoArguments, addArgumentsResult);
                    test.assertEqual(Iterable.create("hello", "there"), jacocoArguments.getArguments());
                });
            });

            runner.testGroup("addJacocoCliJar(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addJacocoCliJar((String)null),
                        new PreConditionFailure("jacococliJarFilePath cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with empty", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addJacocoCliJar(""),
                        new PreConditionFailure("jacococliJarFilePath cannot be empty."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with relative path", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addJacocoCliJar("relative/path.jar");
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("-jar", "relative/path.jar"), jacocoArguments.getArguments());
                });

                runner.test("with rooted path", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addJacocoCliJar("/rooted/path.jar");
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("-jar", "/rooted/path.jar"), jacocoArguments.getArguments());
                });
            });

            runner.testGroup("addJacocoCliJar(Path)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addJacocoCliJar((Path)null),
                        new PreConditionFailure("jacococliJarFilePath cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with relative path", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addJacocoCliJar(Path.parse("relative/path.jar"));
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("-jar", "relative/path.jar"), jacocoArguments.getArguments());
                });

                runner.test("with rooted path", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addJacocoCliJar(Path.parse("/rooted/path.jar"));
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("-jar", "/rooted/path.jar"), jacocoArguments.getArguments());
                });
            });

            runner.testGroup("addJacocoCliJar(File)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addJacocoCliJar((File)null),
                        new PreConditionFailure("jacococliJarFile cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with non-null", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final File file = fileSystem.createRoot("/").await()
                        .getFile("rooted/path.jar").await();
                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addJacocoCliJar(file);
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("-jar", "/rooted/path.jar"), jacocoArguments.getArguments());
                });
            });

            runner.test("addReport()", (Test test) ->
            {
                final T jacocoArguments = creator.run(test);
                final T addReportResult = jacocoArguments.addReport();
                test.assertSame(jacocoArguments, addReportResult);
                test.assertEqual(Iterable.create("report"), jacocoArguments.getArguments());
            });

            runner.testGroup("addCoverageExec(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addCoverageExec((String)null),
                        new PreConditionFailure("coverageExecFilePath cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with empty", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addCoverageExec(""),
                        new PreConditionFailure("coverageExecFilePath cannot be empty."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with relative path", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addCoverageExec("relative/path.exec");
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("relative/path.exec"), jacocoArguments.getArguments());
                });

                runner.test("with rooted path", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addCoverageExec("/rooted/path.exec");
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("/rooted/path.exec"), jacocoArguments.getArguments());
                });
            });

            runner.testGroup("addCoverageExec(Path)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addCoverageExec((Path)null),
                        new PreConditionFailure("coverageExecFilePath cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with relative path", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addCoverageExec(Path.parse("relative/path.exec"));
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("relative/path.exec"), jacocoArguments.getArguments());
                });

                runner.test("with rooted path", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addCoverageExec(Path.parse("/rooted/path.exec"));
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("/rooted/path.exec"), jacocoArguments.getArguments());
                });
            });

            runner.testGroup("addCoverageExec(File)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addCoverageExec((File)null),
                        new PreConditionFailure("coverageExecFile cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with non-null", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final File file = fileSystem.createRoot("/").await()
                        .getFile("rooted/path.exec").await();
                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addCoverageExec(file);
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("/rooted/path.exec"), jacocoArguments.getArguments());
                });
            });

            runner.testGroup("addClassFile(File)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addClassFile((File)null),
                        new PreConditionFailure("classFile cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with file in working folder path", (Test test) ->
                {
                    final Process process = test.getProcess();
                    final Folder currentFolder = process.getCurrentFolder();
                    final File classFile = currentFolder.getFile("file.class").await();

                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addClassFile(classFile);
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("--classfiles", "file.class"), jacocoArguments.getArguments());
                });

                runner.test("with file not under working folder path", (Test test) ->
                {
                    final Process process = test.getProcess();
                    final Folder currentFolder = process.getCurrentFolder();
                    final File classFile = currentFolder.getFile("../file.class").await();

                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addClassFile(classFile);
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("--classfiles", "../file.class"), jacocoArguments.getArguments());
                });
            });

            runner.testGroup("addClassFiles(Iterable<File>)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addClassFiles((Iterable<File>)null),
                        new PreConditionFailure("classFiles cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with empty", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addClassFiles(Iterable.create()),
                        new PreConditionFailure("classFiles cannot be empty."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with one file in working folder path", (Test test) ->
                {
                    final Process process = test.getProcess();
                    final Folder currentFolder = process.getCurrentFolder();
                    final File classFile = currentFolder.getFile("file.class").await();

                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addClassFiles(Iterable.create(classFile));
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("--classfiles", "file.class"), jacocoArguments.getArguments());
                });

                runner.test("with two files in working folder path", (Test test) ->
                {
                    final Process process = test.getProcess();
                    final Folder currentFolder = process.getCurrentFolder();
                    final File classFile1 = currentFolder.getFile("file1.class").await();
                    final File classFile2 = currentFolder.getFile("file2.class").await();

                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addClassFiles(Iterable.create(classFile1, classFile2));
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("--classfiles", "file1.class", "--classfiles", "file2.class"), jacocoArguments.getArguments());
                });
            });

            runner.testGroup("addSourceFiles(Folder)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addSourceFiles((Folder)null),
                        new PreConditionFailure("sourceFolder cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with non-null", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Folder folder = fileSystem.createRoot("/").await()
                        .getFolder("sources").await();
                    final T jacocoArguments = creator.run(test);
                    final T addJacocoCliJarResult = jacocoArguments.addSourceFiles(folder);
                    test.assertSame(jacocoArguments, addJacocoCliJarResult);
                    test.assertEqual(Iterable.create("--sourcefiles", "/sources/"), jacocoArguments.getArguments());
                });
            });

            runner.testGroup("addSourceFiles(Coverage,Folder,Folder)", () ->
            {
                runner.test("with null coverage", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Root root = fileSystem.createRoot("/").await();
                    final Coverage coverage = null;
                    final Folder sources = root.getFolder("sources").await();
                    final Folder tests = root.getFolder("tests").await();
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addSourceFiles(coverage, sources, tests),
                        new PreConditionFailure("coverage cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with null sourceFolder", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Root root = fileSystem.createRoot("/").await();
                    final Coverage coverage = Coverage.None;
                    final Folder sources = null;
                    final Folder tests = root.getFolder("tests").await();
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addSourceFiles(coverage, sources, tests),
                        new PreConditionFailure("sourceFolder cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with null testFolder and None Coverage", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Root root = fileSystem.createRoot("/").await();
                    final Coverage coverage = Coverage.None;
                    final Folder sources = root.getFolder("sources").await();
                    final Folder tests = null;
                    final T jacocoArguments = creator.run(test);
                    final T addSourceFilesResult = jacocoArguments.addSourceFiles(coverage, sources, tests);
                    test.assertSame(jacocoArguments, addSourceFilesResult);
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with null testFolder and Sources Coverage", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Root root = fileSystem.createRoot("/").await();
                    final Coverage coverage = Coverage.Sources;
                    final Folder sources = root.getFolder("sources").await();
                    final Folder tests = null;
                    final T jacocoArguments = creator.run(test);
                    final T addSourceFilesResult = jacocoArguments.addSourceFiles(coverage, sources, tests);
                    test.assertSame(jacocoArguments, addSourceFilesResult);
                    test.assertEqual(Iterable.create("--sourcefiles", "/sources/"), jacocoArguments.getArguments());
                });

                runner.test("with null testFolder and Tests Coverage", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Root root = fileSystem.createRoot("/").await();
                    final Coverage coverage = Coverage.Tests;
                    final Folder sources = root.getFolder("sources").await();
                    final Folder tests = null;
                    final T jacocoArguments = creator.run(test);
                    final T addSourceFilesResult = jacocoArguments.addSourceFiles(coverage, sources, tests);
                    test.assertSame(jacocoArguments, addSourceFilesResult);
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with null testFolder and All Coverage", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Root root = fileSystem.createRoot("/").await();
                    final Coverage coverage = Coverage.All;
                    final Folder sources = root.getFolder("sources").await();
                    final Folder tests = null;
                    final T jacocoArguments = creator.run(test);
                    final T addSourceFilesResult = jacocoArguments.addSourceFiles(coverage, sources, tests);
                    test.assertSame(jacocoArguments, addSourceFilesResult);
                    test.assertEqual(Iterable.create("--sourcefiles", "/sources/"), jacocoArguments.getArguments());
                });

                runner.test("with non-null testFolder and None Coverage", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Root root = fileSystem.createRoot("/").await();
                    final Coverage coverage = Coverage.None;
                    final Folder sources = root.getFolder("sources").await();
                    final Folder tests = root.getFolder("tests").await();
                    final T jacocoArguments = creator.run(test);
                    final T addSourceFilesResult = jacocoArguments.addSourceFiles(coverage, sources, tests);
                    test.assertSame(jacocoArguments, addSourceFilesResult);
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with non-null testFolder and Sources Coverage", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Root root = fileSystem.createRoot("/").await();
                    final Coverage coverage = Coverage.Sources;
                    final Folder sources = root.getFolder("sources").await();
                    final Folder tests = root.getFolder("tests").await();
                    final T jacocoArguments = creator.run(test);
                    final T addSourceFilesResult = jacocoArguments.addSourceFiles(coverage, sources, tests);
                    test.assertSame(jacocoArguments, addSourceFilesResult);
                    test.assertEqual(Iterable.create("--sourcefiles", "/sources/"), jacocoArguments.getArguments());
                });

                runner.test("with non-null testFolder and Tests Coverage", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Root root = fileSystem.createRoot("/").await();
                    final Coverage coverage = Coverage.Tests;
                    final Folder sources = root.getFolder("sources").await();
                    final Folder tests = root.getFolder("tests").await();
                    final T jacocoArguments = creator.run(test);
                    final T addSourceFilesResult = jacocoArguments.addSourceFiles(coverage, sources, tests);
                    test.assertSame(jacocoArguments, addSourceFilesResult);
                    test.assertEqual(Iterable.create("--sourcefiles", "/tests/"), jacocoArguments.getArguments());
                });

                runner.test("with non-null testFolder and All Coverage", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Root root = fileSystem.createRoot("/").await();
                    final Coverage coverage = Coverage.All;
                    final Folder sources = root.getFolder("sources").await();
                    final Folder tests = root.getFolder("tests").await();
                    final T jacocoArguments = creator.run(test);
                    final T addSourceFilesResult = jacocoArguments.addSourceFiles(coverage, sources, tests);
                    test.assertSame(jacocoArguments, addSourceFilesResult);
                    test.assertEqual(Iterable.create("--sourcefiles", "/sources/", "--sourcefiles", "/tests/"), jacocoArguments.getArguments());
                });
            });

            runner.testGroup("addHtml(Folder)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final T jacocoArguments = creator.run(test);
                    test.assertThrows(() -> jacocoArguments.addHtml((Folder)null),
                        new PreConditionFailure("htmlFolder cannot be null."));
                    test.assertEqual(Iterable.create(), jacocoArguments.getArguments());
                });

                runner.test("with non-null", (Test test) ->
                {
                    final InMemoryFileSystem fileSystem = InMemoryFileSystem.create(test.getClock());
                    final Folder htmlFolder = fileSystem.createRoot("/").await()
                        .getFolder("html/folder/").await();
                    final T jacocoArguments = creator.run(test);
                    final T addHtmlResult = jacocoArguments.addHtml(htmlFolder);
                    test.assertSame(jacocoArguments, addHtmlResult);
                    test.assertEqual(Iterable.create("--html", "/html/folder/"), jacocoArguments.getArguments());
                });
            });
        });
    }
}
