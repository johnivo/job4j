package ru.job4j.parser;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 24.09.2019
 */
public class SqlRuParserTest {

    @Test
    public void whenReplaceTodayFromStringThenGetDate() throws ParseException {
        SqlRuParser parser = new SqlRuParser();
        Date time = Calendar.getInstance().getTime();
        String expected = new SimpleDateFormat("d MM yy").format(time);

        String result = parser.setToday();

        assertThat(result, is(expected));
    }

    @Test
    public void whenReplaceYesterdayFromStringThenGetDate() throws ParseException {
        SqlRuParser parser = new SqlRuParser();
        Date time = java.sql.Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        String expected = new SimpleDateFormat("d MM yy").format(time);

        String result = parser.setYesterday();

        assertThat(result, is(expected));
    }

    @Test
    public void whenConvertTimeFromStringThenGetDate() throws ParseException {
        SqlRuParser parser = new SqlRuParser();
        Date expected = java.sql.Timestamp.valueOf(LocalDateTime.of(2019, 9, 22, 9, 54));

        Date result = parser.convertOfDate("22 сен 19, 09:54");
        System.out.println(result);

        assertThat(result, is(expected));
    }

    @Test
    public void whenCheckedNameTopicThenReturnResponseOfBooleanType() {
        SqlRuParser parser = new SqlRuParser();

        assertThat(parser.checkNameTopic("java"), is(true));
        assertThat(parser.checkNameTopic("Java"), is(true));
        assertThat(parser.checkNameTopic("JAVA"), is(true));
        assertThat(parser.checkNameTopic("закрыт"), is(false));
        assertThat(parser.checkNameTopic("javascript"), is(false));
        assertThat(parser.checkNameTopic("java script"), is(false));
    }

}