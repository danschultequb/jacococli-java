package qub;

public interface FakeJacocoCliProcessRunTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(FakeJacocoCliProcessRun.class, () ->
        {
            runner.test("constructor()", (Test test) ->
            {
                final FakeJacocoCliProcessRun run = new FakeJacocoCliProcessRun();
                test.assertNotNull(run);
                test.assertEqual(Path.parse("java"), run.getExecutablePath());
            });
        });
    }
}
