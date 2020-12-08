package qub;

public interface JacocoCliProcessBuilderTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(JacocoCliProcessBuilder.class, () ->
        {
            JacocoCliArgumentsTests.test(runner, (FakeDesktopProcess process) -> JacocoCliProcessBuilder.create(process).await());

            runner.testGroup("create(RealDesktopProcess)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> JacocoCliProcessBuilder.create((RealDesktopProcess)null),
                        new PreConditionFailure("process cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JacocoCliProcessBuilder jacoco = JacocoCliProcessBuilder.create(FakeDesktopProcess.create()).await();
                    test.assertNotNull(jacoco);
                    test.assertEqual(Iterable.create(), jacoco.getArguments());
                });
            });

            runner.testGroup("create(ProcessFactory)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> JacocoCliProcessBuilder.create((ProcessFactory)null),
                        new PreConditionFailure("processFactory cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JacocoCliProcessBuilder jacoco = JacocoCliProcessBuilder.create(FakeDesktopProcess.create().getProcessFactory()).await();
                    test.assertNotNull(jacoco);
                    test.assertEqual(Iterable.create(), jacoco.getArguments());
                });
            });
        });
    }
}
