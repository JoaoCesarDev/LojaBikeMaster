/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormatoData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author jcrfm
 */
public class Data {
    private Data(){}
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
         return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
     }
}
