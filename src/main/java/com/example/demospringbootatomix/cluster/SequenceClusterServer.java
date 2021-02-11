package com.example.demospringbootatomix.cluster;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.demospringbootatomix.config.SequenceClusterConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.atomix.cluster.Node;
import io.atomix.cluster.discovery.BootstrapDiscoveryProvider;
import io.atomix.core.Atomix;
import io.atomix.core.profile.Profile;
import io.atomix.protocols.raft.partition.RaftPartitionGroup;
import io.atomix.storage.StorageLevel;

@Component
public class SequenceClusterServer {
    @Autowired
    SequenceClusterConfig sequenceClusterConfig;

    Atomix atomix;

    public Atomix getAtomix() {
        return this.atomix;
    }

    @PostConstruct
    public void onPostConstruct() {
        List<String> members = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();

        sequenceClusterConfig.getMembers().stream().forEach(member -> {
            members.add(member.getName());
            nodes.add(Node.builder().withId(member.getName()).withAddress(member.getHost(), member.getPort()).build());
        });

        atomix = Atomix.builder()
        .withMemberId(sequenceClusterConfig.getNode().getName())
        .withAddress(sequenceClusterConfig.getNode().getHost(), sequenceClusterConfig.getNode().getPort())
        .withMembershipProvider(BootstrapDiscoveryProvider.builder().withNodes(nodes).build())
        .withManagementGroup(
            RaftPartitionGroup.builder("system")
            .withMembers(members)
            .withNumPartitions(1)
            .withStorageLevel(StorageLevel.DISK)
            .withDataDirectory(new File(sequenceClusterConfig.getStoragePath(), "system"))
            .build())
        .withPartitionGroups(
            RaftPartitionGroup.builder("data")
            .withMembers(members)
            .withNumPartitions(10)
            .withStorageLevel(StorageLevel.DISK)
            .withDataDirectory(new File(sequenceClusterConfig.getStoragePath(), "data"))
            .build())
        .build();

        atomix.start().join();
    }
}
