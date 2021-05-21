/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionalprogramming;

import java.util.Iterator;

/**
 *
 * @author abdalla
 */
public interface ClosableIterator<T> extends Iterator<T>, AutoCloseable {

    void close();
}
