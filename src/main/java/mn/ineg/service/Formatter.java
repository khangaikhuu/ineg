/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.service;

import org.springframework.format.Parser;
import org.springframework.format.Printer;

/**
 *
 * @author developer
 */
public interface Formatter<T> extends Printer<T>, Parser<T> {
}
