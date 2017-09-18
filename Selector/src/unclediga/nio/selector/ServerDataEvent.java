/********************************************************
 *  Опубликовано: 16 мая 2016 г.                        *
 *  Технотрек Mail.ru Group, МФТИ                       *
 *  Курс "Разработка на Java"                           *
 *  Лекция №12 "NIO"                 	                *
 *  Лектор - Рустам Кильдиев            		        *
 *  Слайды: http://www.docme.ru/WpN5 			        *
 *******************************************************/
package unclediga.nio.selector;

/**
 *
 */

import java.nio.channels.SocketChannel;

class ServerDataEvent {
    NioServer server;
    public SocketChannel socket;
    public byte[] data;

    ServerDataEvent(NioServer server, SocketChannel socket, byte[] data) {
        this.server = server;
        this.socket = socket;
        this.data = data;
    }
}
