package qub;

public interface JacocoCliProcessBuilderTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(JacocoCliProcessBuilder.class, () ->
        {
            JacocoCliArgumentsTests.test(runner, (Test test) -> JacocoCliProcessBuilder.create(test.getProcess()).await());

            runner.testGroup("create(Process)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> JacocoCliProcessBuilder.create((Process)null),
                        new PreConditionFailure("process cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JacocoCliProcessBuilder jacoco = JacocoCliProcessBuilder.create(test.getProcess()).await();
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
                    final JacocoCliProcessBuilder jacoco = JacocoCliProcessBuilder.create(test.getProcess().getProcessFactory()).await();
                    test.assertNotNull(jacoco);
                    test.assertEqual(Iterable.create(), jacoco.getArguments());
                });
            });
        });
    }
}
