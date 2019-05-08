# streamingfileupload-error
Demonstrates an issue with Micronaut 1.1.0 and `StreamingFileUpload` where accessing the bytebuffer of `PartData` causes an exception.

Steps to reproduce:

1. start the server (using gradle run or by starting Application class in a JVM)
2. access http://localhost:8080/index.html from a browser and initiate a file upload using the supplied form
3. watch the server's log output for the following exception:

```
java.lang.ClassCastException: class io.netty.buffer.UnpooledDuplicatedByteBuf cannot be cast to class io.netty.buffer.CompositeByteBuf (io.netty.buffer.UnpooledDuplicatedByteBuf and io.netty.buffer.CompositeByteBuf are in unnamed module of loader 'app')
	at io.micronaut.http.server.netty.HttpDataReference$Component.lambda$getByteBuf$0(HttpDataReference.java:216)
	at io.micronaut.http.server.netty.HttpDataReference$1.release(HttpDataReference.java:140)
	at io.micronaut.http.server.netty.multipart.NettyPartData.getByteBuffer(NettyPartData.java:86)
	at example.UploadController.lambda$upload$0(UploadController.java:22)
	at io.reactivex.internal.operators.flowable.FlowableMap$MapSubscriber.onNext(FlowableMap.java:63)
  
 ```  
