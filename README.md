Spring Boot JPA集成分布式事务Seata
-------------------

# Spring boot 1.5.x集成Seata分布式事务

1. 集成RestTemplate
2. 集成Spring MVC
3. 集成Spring Cloud Feign Client

## 运行
下载Seata 0.6.0版本，启动服务，也可以使用[这个](https://github.com/yuebo/seata-server-starter)


作为测试，你可以使用[seata的修改版本](https://github.com/yuebo/seata)

此版本对支持的数据库字段类型做了兼容，支持数字和日期，官方版本对bigint和timestamp等类型兼容性不强。

运行SeataStarterApplication类


使用方式

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/seata?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false
    username: root
    password: password
    driver-class-name: com.mysql.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      validation-query: select 1
    initialize: false
  jpa:
    hibernate:
      ddl-auto: update
  seata:
    group-id: group1
```

registry.conf
```text
registry {
  # file 、nacos 、eureka、redis、zk
  type = "file"
  file {
    name = "file.conf"
  }
}

config {
  # file、nacos 、apollo、zk
  type = "file"
  file {
    name = "file.conf"
  }
}
```
file.conf
```text
transport {
  # tcp udt unix-domain-socket
  type = "TCP"
  #NIO NATIVE
  server = "NIO"
  #enable heartbeat
  heartbeat = true
  #thread factory for netty
  thread-factory {
    boss-thread-prefix = "NettyBoss"
    worker-thread-prefix = "NettyServerNIOWorker"
    server-executor-thread-prefix = "NettyServerBizHandler"
    share-boss-worker = false
    client-selector-thread-prefix = "NettyClientSelector"
    client-selector-thread-size = 1
    client-worker-thread-prefix = "NettyClientWorkerThread"
    # netty boss thread size,will not be used for UDT
    boss-thread-size = 1
    #auto default pin or 8
    worker-thread-size = 8
  }
}
store {
  # branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions
  max-branch-session-size = 16384
  # globe session size , if exceeded throws exceptions
  max-global-session-size = 512
  # file buffer size , if exceeded allocate new buffer
  file-write-buffer-cache-size = 16384
  # when recover batch read size
  session.reload.read_size = 100
}
service {
  #vgroup->rgroup
  vgroup_mapping.group1 = "default"
  #only support single node
  default.grouplist = "127.0.0.1:8091"
  #degrade current not support
  enableDegrade = false
  #disable
  disable = false
}

client {
  async.commit.buffer.limit = 10000
  lock {
    retry.internal = 10
    retry.times = 30
  }
}

## transaction log store
store {
  ## store mode: file、db
  mode = "file"

  ## file store
  file {
    dir = "file_store/data"

    # branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions
    max-branch-session-size = 16384
    # globe session size , if exceeded throws exceptions
    max-global-session-size = 512
    # file buffer size , if exceeded allocate new buffer
    file-write-buffer-cache-size = 16384
    # when recover batch read size
    session.reload.read_size = 100
  }

}
```

使用方法
```java
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @GlobalTransactional
    public void insert(Account account){
        accountRepository.save(account);
        throw new RuntimeException("rollback test");
    }
}
```

远程调用
```java
@FeignClient(url = "http://localhost:8080/test",name = "test")
public interface TestClient {
    @GetMapping
    String test();
}

```
