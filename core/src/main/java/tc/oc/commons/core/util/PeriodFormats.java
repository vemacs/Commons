package tc.oc.commons.core.util;

import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class PeriodFormats {
    public static final PeriodFormatter SHORTHAND = new PeriodFormatterBuilder()
            .appendYears().appendSuffix("y")
            .appendMonths().appendSuffix("mo")
            .appendDays().appendSuffix("d")
            .appendHours().appendSuffix("h")
            .appendMinutes().appendSuffix("m")
            .appendSeconds().appendSeparatorIfFieldsBefore("s") // numbers with no units assumed to be seconds
            .toFormatter();

    public static final PeriodFormatter COLONS = new PeriodFormatterBuilder()
            .appendHours().appendSeparatorIfFieldsBefore(":")
            .minimumPrintedDigits(2).printZeroAlways().appendMinutes().appendSeparator(":")
            .minimumPrintedDigits(2).appendSeconds()
            .toFormatter();
}
