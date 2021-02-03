package org.geekbang.thinking.in.spring.configuration.meta;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    //  <users:user id="7" name="XMl-USER" city="BEIJING"/> 知道这个bean的处理User.class 类型
    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        setPropertyValue("id", element, builder);
        setPropertyValue("name", element, builder);
        setPropertyValue("city", element, builder);
    }

    private void setPropertyValue(String name, Element element, BeanDefinitionBuilder builder) {
        String value = element.getAttribute(name);
        if (StringUtils.hasText(value)) {
            builder.addPropertyValue(name, value);
        }
    }
}
