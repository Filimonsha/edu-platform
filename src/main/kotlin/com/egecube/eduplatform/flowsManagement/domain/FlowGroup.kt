package com.egecube.eduplatform.flowsManagement.domain

import jakarta.persistence.*
import net.minidev.json.annotate.JsonIgnore

@Entity
class FlowGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String?,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_id", insertable = false, updatable = false)
    val flow: Flow?,

    @Column(name = "flow_id")
    val flowId: Long,
)