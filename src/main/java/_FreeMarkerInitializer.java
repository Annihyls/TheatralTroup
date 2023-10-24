import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.util.Locale;

public class _FreeMarkerInitializer {
    public static Configuration getContext() {
        //Configure FreeMarker
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(_FreeMarkerInitializer.class, "/templates");
        configuration.setLocale(Locale.FRANCE);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return configuration;
    }
}
