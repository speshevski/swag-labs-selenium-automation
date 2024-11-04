package data;

public final class Timeouts {
    // todo: instead of hardcoding, like in CommonStrings data class
    // todo: use values from properties files with timeouts for each environment
    public static final int IMPLICIT_TIMEOUT = 1;
    public static final int PAGE_LOAD_TIMEOUT = 3;
    public static final int SCRIPT_LOAD_TIMEOUT = 3;

    public static final int TIME_SHORTEST = 1;
    public static final int TIME_SHORTER = 3;
    public static final int TIME_SHORT = 5;
    public static final int TIME_MEDIUM = 10;
    public static final int TIME_LONG = 15;
    public static final int TIME_LONGER = 30;
    public static final int TIME_LONGEST = 60;
}
