package s13o.test.search.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s13o.test.search.index.api.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/19/2018
 */
@Service
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class Api {

    private Index index;

    public Api(@Autowired Index index) {
        this.index = index;
    }

    @POST
    @Path("/add")
    public Doc add(@FormParam("key") String key, @FormParam("content") String content) throws AlreadyExistsException, IllegalContentException {
        Doc doc = new Doc(key, content);
        index.add(doc);
        return doc;
    }

    @GET
    @Path("/get/{key}")
    public Doc get(@PathParam("key") String key) {
        return index.get(key).findAny().map(Api::match).get();
    }

    @GET
    @Path("/and/{criteria}")
    public Collection<Doc> and(@PathParam("criteria") String criteria) throws WrongParametersException {
        return index.findAll(criteria).map(Api::match).collect(Collectors.toList());
    }

    @GET
    @Path("/or/{criteria}")
    public Collection<Doc> or(@PathParam("criteria") String criteria) throws WrongParametersException {
        return index.findAny(criteria).map(Api::match).collect(Collectors.toList());
    }

    @GET
    @Path("/all")
    public Collection<Doc> all() throws WrongParametersException {
        return index.getAll().map(Api::match).collect(Collectors.toList());
    }

    private static Doc match(Document d) {
        return d instanceof Doc ? (Doc) d : new Doc(d.getKey(), String.valueOf(d.getContent()));
    }
}
