/********************************************************
 *  Опубликовано: 16 мая 2016 г.                        *
 *  Технотрек Mail.ru Group, МФТИ                       *
 *  Курс "Разработка на Java"                           *
 *  Лекция №12 "NIO"                 	                *
 *  Лектор - Рустам Кильдиев            		        *
 *  Слайды: http://www.docme.ru/WpN5 			        *
 *******************************************************/
package unclediga.nio.selector;

import java.nio.channels.SocketChannel;

/**
 *
 */
class ChangeRequest {
    static final int CHANGEOPS = 2;

    public SocketChannel socket;
    public int type;
    int ops;

    ChangeRequest(SocketChannel socket, int type, int ops) {
        this.socket = socket;
        this.type = type;
        this.ops = ops;
    }
}
