package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PathMatcher;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

public class CustomizedResourcePatternResolverDemo {
    public static void main(String[] args) throws IOException {
        //  匹配所有对应的java文件
        String pattern = System.getProperty("user.dir")+"/thinking-in-spring/resource/src/main/java/org/geekbang/thinking/in/spring/resource/*.java";
        //  设置加载器为FileSystemResourceLoader
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

//        pathMatchingResourcePatternResolver.setPathMatcher(new CustomPathMatcher());
        Resource[] resources = pathMatchingResourcePatternResolver.getResources(pattern);

        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
    }

    static class CustomPathMatcher implements PathMatcher{

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
