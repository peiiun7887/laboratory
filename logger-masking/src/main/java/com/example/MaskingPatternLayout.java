package com.example;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class MaskingPatternLayout extends PatternLayout {

    private Pattern multiLinePattern;
    private List<String> maskPatterns = new ArrayList<>();

    public void addMaskPattern(String pattern) {
        maskPatterns.add(pattern);
        multiLinePattern = Pattern.compile(String.join("|", maskPatterns), Pattern.MULTILINE);
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event));
    }

    private String maskMessage(String message) {
        if (message == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(message);
        Matcher matcher = multiLinePattern.matcher(sb);
        while (matcher.find()) {
            IntStream.rangeClosed(1, matcher.groupCount())
                    .forEach(group -> {
                        if (matcher.group(group) != null) {
                            // CardNo masking :length >=12 show prefix 9 and suffix 1 digit, otherwise show prefix 9 digit only.
                            if (matcher.group().matches(".*\\b(cardNo=)\\b.*")) {
                                int cardNoLength = matcher.end(group) - matcher.start(group);
                                int maskStart = matcher.end(group) - cardNoLength + 9;
                                int maskEnd = cardNoLength - 9 >= 3 ? matcher.end(group) - 1 : matcher.end(group);
                                IntStream.range(maskStart, maskEnd)
                                        .forEach(i -> sb.setCharAt(i, '*'));

                            // PhoneNo masking : middle 3-5 digits
                            } else if(matcher.group().matches(".*\\b(phoneNo=)\\b.*")) {
                                int maskStart = matcher.end(group) - 6;
                                int maskEnd = matcher.end(group) - 3;
                                IntStream.range(maskStart, maskEnd)
                                        .forEach(i -> sb.setCharAt(i, '*'));

                            // CellPhoneNo masking : middle 3-5 digits
                            } else if(matcher.group().matches(".*\\b(cellPhoneNo=)\\b.*")) {
                                int maskStart = matcher.end(group) - 6;
                                int maskEnd = matcher.end(group) - 3;
                                IntStream.range(maskStart, maskEnd)
                                        .forEach(i -> sb.setCharAt(i, '*'));

                            } else if(matcher.group().matches(".*\\b(email=)\\b.*")) {
                            } else if(matcher.group().matches(".*\\b(address=)\\b.*")) {
                            } else if(matcher.group().matches(".*\\b(idNo=)\\b.*")) {
                            } else if(matcher.group().matches(".*\\b(userName=)\\b.*")) {


                            } else {
                                IntStream.range(matcher.start(group), matcher.end(group))
                                        .forEach(i -> sb.setCharAt(i, '*'));
                            }
                        }
                    });
        }
        return sb.toString();
    }

    private void maskMiddleDigit(StringBuilder sb, Matcher matcher, int group, int digitCount) {
        int dataLength = matcher.end(group) - matcher.start(group);
        int maskStart = matcher.end(group) - 3 - digitCount;
        int maskEnd = matcher.end(group) - 3;
        IntStream.range(maskStart, maskEnd)
                .forEach(i -> sb.setCharAt(i, '*'));
    }

}
