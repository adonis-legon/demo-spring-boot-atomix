package com.example.demospringbootatomix.business;

import javax.annotation.PostConstruct;

import com.example.demospringbootatomix.cluster.SequenceClusterServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.atomix.protocols.raft.MultiRaftProtocol;
import io.atomix.protocols.raft.MultiRaftProtocolBuilder;
import io.atomix.protocols.raft.ReadConsistency;

@Service
public class SequenceService {
    public static final String SEQUENCE_NAME = "Sequence";

    @Autowired
    SequenceClusterServer sequenceClusterServer;

    @PostConstruct
    public void onPostConstruct() {
        sequenceClusterServer.getAtomix().getAtomicCounter(SEQUENCE_NAME);
    }

    public Long getCurrent() {
        return sequenceClusterServer.getAtomix().getAtomicCounter(SEQUENCE_NAME).get();
    }

    public Long getNext() {
        return sequenceClusterServer.getAtomix().getAtomicCounter(SEQUENCE_NAME).incrementAndGet();
    }

    public Long reset() {
        sequenceClusterServer.getAtomix().getAtomicCounter(SEQUENCE_NAME).set(0);
        return 0L;
    }
}
