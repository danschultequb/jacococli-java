package qub;

public class JacocoCliProcessBuilder extends JavaProcessBuilderDecorator<JacocoCliProcessBuilder> implements JacocoCliArguments<JacocoCliProcessBuilder>
{
    private JacocoCliProcessBuilder(JavaProcessBuilder javaProcessBuilder)
    {
        super(javaProcessBuilder);
    }

    /**
     * Get a JavaProcessBuilder from the provided Process.
     * @param process The Process to get the JavaProcessBuilder from.
     * @return The JavaProcessBuilder.
     */
    public static Result<JacocoCliProcessBuilder> create(DesktopProcess process)
    {
        PreCondition.assertNotNull(process, "process");

        return JacocoCliProcessBuilder.create(process.getProcessFactory());
    }

    /**
     * Get a JavaProcessBuilder from the provided ProcessFactory.
     * @param processFactory The ProcessFactory to get the JavaProcessBuilder from.
     * @return The JavaProcessBuilder.
     */
    public static Result<JacocoCliProcessBuilder> create(ProcessFactory processFactory)
    {
        PreCondition.assertNotNull(processFactory, "processFactory");

        return Result.create(() ->
        {
            return new JacocoCliProcessBuilder(JavaProcessBuilder.create(processFactory).await());
        });
    }
}
