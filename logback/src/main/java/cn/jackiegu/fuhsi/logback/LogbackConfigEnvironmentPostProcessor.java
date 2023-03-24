package cn.jackiegu.fuhsi.logback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.HashMap;
import java.util.Map;

/**
 * Logback日志组件配置处理器
 * 添加logbackConfigProperties属性资源到environment实例, 该属性资源配置了logging.config文件路径
 *
 * @author JackieGu
 * @date 2022/4/1
 */
public class LogbackConfigEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    public static final int DEFAULT_ORDER = ConfigFileApplicationListener.DEFAULT_ORDER + 1;

    private static final String LOGBACK_CONFIG_PROPERTY = "logbackConfigProperties";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> source = new HashMap<>();
        source.put(LoggingApplicationListener.CONFIG_PROPERTY,
                "classpath:cn/jackiegu/fuhsi/logback/config/logback-default.xml");
        PropertySource<Map<String, Object>> propertySource = new MapPropertySource(LOGBACK_CONFIG_PROPERTY, source);
        environment.getPropertySources()
                .addBefore(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, propertySource);
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }
}
