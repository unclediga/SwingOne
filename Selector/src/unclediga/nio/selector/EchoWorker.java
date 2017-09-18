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
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
class EchoWorker implements Runnable {
    private final List<ServerDataEvent> queue = new LinkedList<>();

    void processData(NioServer server, SocketChannel socket, byte[] data, int count) {
        byte[] dataCopy = new byte[count];
        System.arraycopy(data, 0, dataCopy, 0, count);
        synchronized (queue) {
            queue.add(new ServerDataEvent(server, socket, dataCopy));
            queue.notify();
        }
    }

    public void run() {
        ServerDataEvent dataEvent;
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Recieved = " + new String(queue.get(0).data));
                dataEvent = queue.remove(0);
            }
            dataEvent.server.send(dataEvent.socket, dataEvent.data);
        }
    }
}
