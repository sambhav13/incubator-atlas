/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.atlas.web.rest;

import org.apache.atlas.exception.AtlasBaseException;
import org.apache.atlas.model.instance.AtlasRelationship;
import org.apache.atlas.repository.store.graph.AtlasRelationshipStore;
import org.apache.atlas.utils.AtlasPerfTracer;
import org.apache.atlas.web.util.Servlets;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * REST interface for entity relationships.
 */
@Path("v2/relationship")
@Singleton
@Service
public class RelationshipREST {
    private static final Logger PERF_LOG = AtlasPerfTracer.getPerfLogger("rest.RelationshipREST");

    private final AtlasRelationshipStore relationshipStore;

    @Inject
    public RelationshipREST(AtlasRelationshipStore relationshipStore) {
        this.relationshipStore = relationshipStore;
    }

    /**
     * Create a new relationship between entities.
     */
    @POST
    @Consumes(Servlets.JSON_MEDIA_TYPE)
    @Produces(Servlets.JSON_MEDIA_TYPE)
    public AtlasRelationship create(AtlasRelationship relationship) throws AtlasBaseException {
        AtlasPerfTracer perf = null;

        try {
            if (AtlasPerfTracer.isPerfTraceEnabled(PERF_LOG)) {
                perf = AtlasPerfTracer.getPerfTracer(PERF_LOG, "RelationshipREST.create(" + relationship + ")");
            }

            return relationshipStore.create(relationship);

        } finally {
            AtlasPerfTracer.log(perf);
        }
    }

    /**
     * Update an existing relationship between entities.
     */
    @PUT
    @Consumes(Servlets.JSON_MEDIA_TYPE)
    @Produces(Servlets.JSON_MEDIA_TYPE)
    public AtlasRelationship update(AtlasRelationship relationship) throws AtlasBaseException {
        AtlasPerfTracer perf = null;

        try {
            if (AtlasPerfTracer.isPerfTraceEnabled(PERF_LOG)) {
                perf = AtlasPerfTracer.getPerfTracer(PERF_LOG, "RelationshipREST.update(" + relationship + ")");
            }

            return relationshipStore.update(relationship);

        } finally {
            AtlasPerfTracer.log(perf);
        }
    }

    /**
     * Get relationship information between entities using guid.
     */
    @GET
    @Path("/guid/{guid}")
    @Consumes(Servlets.JSON_MEDIA_TYPE)
    @Produces(Servlets.JSON_MEDIA_TYPE)
    public AtlasRelationship getById(@PathParam("guid") String guid) throws AtlasBaseException {
        AtlasPerfTracer perf = null;

        try {
            if (AtlasPerfTracer.isPerfTraceEnabled(PERF_LOG)) {
                perf = AtlasPerfTracer.getPerfTracer(PERF_LOG, "RelationshipREST.getById(" + guid + ")");
            }

            return relationshipStore.getById(guid);

        } finally {
            AtlasPerfTracer.log(perf);
        }
    }

    /**
     * Delete a relationship between entities using guid.
     */
    @DELETE
    @Path("/guid/{guid}")
    @Consumes(Servlets.JSON_MEDIA_TYPE)
    @Produces(Servlets.JSON_MEDIA_TYPE)
    public void deleteById(@PathParam("guid") String guid) throws AtlasBaseException {
        AtlasPerfTracer perf = null;

        try {
            if (AtlasPerfTracer.isPerfTraceEnabled(PERF_LOG)) {
                perf = AtlasPerfTracer.getPerfTracer(PERF_LOG, "RelationshipREST.deleteById(" + guid + ")");
            }

            relationshipStore.deleteById(guid);
        } finally {
            AtlasPerfTracer.log(perf);
        }
    }
}