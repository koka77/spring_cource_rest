package org.udemycource.spring.rest.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * Вместо указания xml файла контекста
     * <init-param>
     *       <param-name>contextConfigLocation</param-name>
     *       <param-value>/WEB-INF/applicationContext.xml</param-value>
     *     </init-param>
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class};
    }

    /**
     *   <servlet-mapping>
     *     <servlet-name>dispatcher</servlet-name>
     *     <url-pattern>/</url-pattern>
     *   </servlet-mapping>
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
