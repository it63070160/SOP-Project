package com.example.grpcservice.controller;

import com.proto.dummy.DummyMessage;
import com.proto.dummy.DummyServiceGrpc;
import io.grpc.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
public class DummyGatewayService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/bookName/{bookId}", method = RequestMethod.GET)
    public String getBookName(@PathVariable String bookId) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",50055)
                .usePlaintext().build();
        DummyServiceGrpc.DummyServiceBlockingStub syncClient
                = DummyServiceGrpc.newBlockingStub(channel);

        DummyServiceGrpc.DummyServiceBlockingStub dummyClient
                = DummyServiceGrpc.newBlockingStub(channel);
        Object str = rabbitTemplate.convertSendAndReceive("MyDirectExchange", "getBook", bookId);
        String name = (String) str;
// created a protocol buffer greeting message
        DummyMessage requestMessage = DummyMessage.newBuilder().setTxt(name).build();
// call the RPC and get back a GreetResponse (Protocol Buffers)
        DummyMessage responseMessage = dummyClient.sayHi(requestMessage);
        channel.shutdown();
        return responseMessage.getTxt();
}}
