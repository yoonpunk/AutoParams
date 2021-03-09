package org.javaunit.autoparams;

import java.util.Optional;

final class BooleanArgumentGenerator implements ArgumentGenerator {

    @Override
    public Optional<Object> generate(ParameterDescriptor parameter, ArgumentGenerationContext context) {
        Class<?> type = parameter.getType();
        return type.equals(boolean.class) || type.equals(Boolean.class) ? factory() : Optional.empty();
    }

    private Optional<Object> factory() {
        return Optional.of(RANDOM.nextInt() % 2 == 0);
    }
    
}