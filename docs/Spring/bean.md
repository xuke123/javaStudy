# bean声明周期

① 实例化 Bean：对于 BeanFactory 容器，当客户向容器请求一个尚未初始化的 Bean 时，或初始化 Bean 的时候需要注入另一个尚未初始化的依赖时，容器就会调用 createBean 进行实例化。对于 ApplicationContext 容器，当容器启动结束后，通过获取 BeanDefinition 对象中的信息，实例化所有的 Bean；
② 设置对象属性（依赖注入）：实例化后的对象被封装在 BeanWrapper 对象中，紧接着 Spring 根据 BeanDefinition 中的信息以及通过 BeanWrapper 提供的设置属性的接口完成依赖注入；
③ 处理 Aware 接口：Spring 会检测该对象是否实现了 xxxAware 接口，并将相关的 xxxAware 实例注入给 Bean：
如果这个 Bean 已经实现了 BeanNameAware 接口，会调用它实现的 setBeanName(String BeanId) 方法，此处传递的就是 Spring 配置文件中 Bean 的 id 值；
如果这个 Bean 已经实现了 BeanFactoryAware 接口，会调用它实现的 setBeanFactory() 方法，传递的是 Spring 工厂自身；
如果这个 Bean 已经实现了 ApplicationContextAware 接口，会调用 setApplicationContext(ApplicationContext) 方法，传入 Spring 上下文；
④ BeanPostProcessor：如果想对 Bean 进行一些自定义的处理，那么可以让 Bean 实现了 BeanPostProcessor 接口，那将会调用 postProcessBeforeInitialization(Object obj, String s) 方法；
⑤ InitializingBean 与 init-method：如果 Bean 在 Spring 配置文件中配置了 init-method 属性，则会自动调用其配置的初始化方法；
⑥ 如果这个 Bean 实现了 BeanPostProcessor 接口，将会调用 postProcessAfterInitialization(Object obj, String s) 方法；由于这个方法是在 Bean 初始化结束时调用的，因而可以被应用于内存或缓存技术；
