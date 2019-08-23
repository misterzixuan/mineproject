1.springboot注解实现多数据源重点在于：AbstractRoutingDatace抽象类，它是spring-jdbc提供的用于配置多数据源的类，该类源码执行流程如下。
    1.1：它的targetDataSources属性是一个map类型。key是数据源对应的别名，value是Datasource。
    1.2：defaultTargetDataSource是Object类型用于存放默认的Datasource，
    1.3：afterPropertiesSet方法，会将它的targetDataSources（map类型）数据复制到resolvedDataSources（map类型）属性中。
    1.4：AbstractRoutingDatace抽象类获取连接的方法是getConnection，该方法调用determineTargetDataSource（）获取数据源DataSource对象
         在通过该对象获取connection连接。
    1.5：determineTargetDataSource：调用determineCurrentLookupKey方法获取key（即上述map属性的key），通过该key获取
         resolvedDataSources（key-value）中的value（DataSource）。当通过key无法找到value时，会将defaultTargetDataSource
         对象复制给DataSource并返回，如果赋值失败，则会因为没有DataSource实例而报错。
         
2.注意项：super.afterPropertiesSet();方法不需要手动调用，AbstractRoutingDataSource实现了InitializingBean接口其中afterPropertiesSet方法便是来自此接口，该方法是在
        bean被初始化时属性设置完后，会调用该方法的创建bean的时候 spring容器会自动调用，当targetDataSource在第一次同步到
        ResolveDataSource之后数据发生变化，则需要手动调用该方法同步到resolvedDataSources中。