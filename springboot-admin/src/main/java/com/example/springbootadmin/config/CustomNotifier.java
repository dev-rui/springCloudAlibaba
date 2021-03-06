package com.example.springbootadmin.config;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @Author hrh
 * @Date 2020/6/8 15:58
 * @Version 1.0
 */
@Component
@Log4j2
public class CustomNotifier  extends AbstractStatusChangeNotifier {
   

    public CustomNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> {
            if (event instanceof InstanceStatusChangedEvent) {
                log.info("Instance {} ({}) is {}", instance.getRegistration().getName(), event.getInstance(),
                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());

                String status = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();

                switch (status) {
                    // 健康检查没通过
                    case "DOWN":
                        log.info("发送 健康检查没通过 的通知！");
                        break;
                    // 服务离线
                    case "OFFLINE":
                        log.info("发送 服务离线 的通知！");
                        break;
                    //服务上线
                    case "UP":
                        log.info("发送 服务上线 的通知！");
                        break;
                    // 服务未知异常
                    case "UNKNOWN":
                        log.info("发送 服务未知异常 的通知！");
                        break;
                    default:
                        break;
                }

            } else {
                log.info("Instance {} ({}) {}", instance.getRegistration().getName(), event.getInstance(),
                        event.getType());
            }
        });
    }
}
