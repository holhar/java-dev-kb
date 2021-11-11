package de.holhar.java_dev_kb.training.pcps.ch01_container.s0125_SpEL_s0128_SpEL_references;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "de.holhar.java_dev_kb.training.pcps.ch01_container.s0125_SpEL_s0128_SpEL_references")
public class SpELApplication {

    @Autowired
    private ApplicationContext applicationContext;

    private static final Logger logger = LoggerFactory.getLogger(SpELApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpELApplication.class, args);
    }

    /**
     * Q1.25
     * The Spring Expression Language (“SpEL” for short) is a powerful expression language that supports querying and
     * manipulating an object graph at runtime. The language syntax is similar to Unified EL but offers additional
     * features, most notably method invocation and basic string templating functionality.
     */
    @PostConstruct
    private void init() {

        // Necessary to resolve systemProperties object in below example
        BeanFactoryResolver factoryResolver = new BeanFactoryResolver(applicationContext);

        // is required with expression templating
        TemplateParserContext parserContext = new TemplateParserContext();

        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setBeanResolver(factoryResolver);

        MySpELContext mySpELContext = new MySpELContext();
        mySpELContext.setName("context");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        mySpELContext.setNumbers(numbers);
        Map<String, Integer> integerStringMap = Map.of("one", 1, "two", 2, "three", 3, "four", 4);
        mySpELContext.setIntegerStringMap(integerStringMap);
        evaluationContext.setRootObject(mySpELContext);

        // Turn on:
        // - auto null reference initialization
        // - auto collection growing
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);
        ExpressionParser parser = new SpelExpressionParser(config);

        // https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions-language-ref
        // The expression language supports the following functionality:
        // - Literal expressions
        Expression exp = parser.parseExpression("'Hello World'");
        logger.info("message: {}", exp.getValue());

        // - Properties, arrays, lists, maps (variables)
        exp = parser.parseExpression("numbers[3]");
        logger.info("numbers - 3rd item: {}", exp.getValue(evaluationContext));
        exp = parser.parseExpression("integerStringMap['two']");
        logger.info("integerStringMap - 2nd item: {}", exp.getValue(evaluationContext));
        exp = parser.parseExpression("#{@systemProperties['os.name']}", parserContext);
        logger.info("OS name from system props: {}", exp.getValue(evaluationContext));

        // - Method invocation
        exp = parser.parseExpression("name.toUpperCase()");
        logger.info("context says hello: {}", exp.getValue(evaluationContext));

        // SpEl further supports:
        // - Operators
        // - User defined functions (implemented as static functions)
        // - Referencing Spring beans in a bean factory (application context)
        // - Collection selection expressions
        // - Collection projection
        // - Expression templating

        /**
         * Q1.28:
         * SpEL can reference:
         * - Static methods and static properties/fields
         * - Properties and methods in Spring beans
         * - Properties and methods in Java objects with references stored in SpEL variables
         * - (JVM) system properties
         * - System environment properties
         * - Spring application environment
         */
    }

    static class MySpELContext {

        private String name;
        private List<Integer> numbers;
        private Map<String, Integer> integerStringMap;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Integer> getNumbers() {
            return numbers;
        }

        public void setNumbers(List<Integer> numbers) {
            this.numbers = numbers;
        }

        public Map<String, Integer> getIntegerStringMap() {
            return integerStringMap;
        }

        public void setIntegerStringMap(Map<String, Integer> integerStringMap) {
            this.integerStringMap = integerStringMap;
        }
    }
}
