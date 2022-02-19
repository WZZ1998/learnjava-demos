/**
 * @author rx_w@outlook.com
 * @date 10/14/21 4:51 下午
 * @description
 * @version 1.0
 */
module com.rxcay.learnjava.demos {
   requires java.net.http;
   requires com.fasterxml.jackson.databind;
   opens com.rxcay.learnjava.demos.model;
}