package autoparams.generator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

final class Factories {

    private static ThreadLocalRandom random() {
        return ThreadLocalRandom.current();
    }

    public static boolean createBoolean() {
        return random().nextInt() % 2 == 0;
    }

    public static long createLong() {
        return random().nextLong();
    }

    public static float createFloat() {
        return random().nextFloat();
    }

    public static double createDouble() {
        return random().nextDouble();
    }

    public static char createChar() {
        return (char) random().nextInt(Character.MAX_VALUE + 1);
    }

    public static BigInteger createBigInteger() {
        int maxBitLength = 256;
        return new BigInteger(maxBitLength, random());
    }

    public static BigDecimal createBigDecimal() {
        return new BigDecimal(random().nextInt());
    }

    public static Duration createDuration() {
        return Duration.ofMillis(createLong());
    }

    public static LocalDate createLocalDate() {
        final LocalDate today = LocalDate.now();
        return today.plusDays(random().nextInt(-28, 29));
    }

    public static LocalTime createLocalTime() {
        long inclusiveMin = LocalTime.MIN.toNanoOfDay();
        long exclusiveMax = LocalTime.MAX.toNanoOfDay() + 1;
        long randomNanoOfDay = random().nextLong(inclusiveMin, exclusiveMax);
        return LocalTime.ofNanoOfDay(randomNanoOfDay);
    }

    public static LocalDateTime createLocalDateTime() {
        return LocalDateTime.of(createLocalDate(), createLocalTime());
    }

    public static Class<?> createClass() {
        return String.class;
    }

    public static IntStream createIntStream() {
        return IntStream.generate(random()::nextInt).limit(3);
    }

    public static LongStream createLongStream() {
        return LongStream.generate(Factories::createLong).limit(3);
    }

    public static DoubleStream createDoubleStream() {
        return DoubleStream.generate(Factories::createDouble).limit(3);
    }

    public static Period createPeriod(ObjectGenerationContext context) {
        final Period period = Period.between(
            context.generate(LocalDate.class),
            context.generate(LocalDate.class));
        return period.isNegative() ? period.negated() : period;
    }
}
