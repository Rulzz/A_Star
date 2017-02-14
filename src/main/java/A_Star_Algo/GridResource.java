package A_Star_Algo;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import DTO.GridDTO;
import DTO.GridDTOConverter;

@Provider
@Path("/GridResource") 
public class GridResource {
	GridService service = new GridService();
	ObjectMapper mapper = new ObjectMapper();
	GridDTOConverter gridDTOConverter = new GridDTOConverter();
	
	@GET 
	@Path("/default") 
	@Produces(MediaType.APPLICATION_JSON) 
	public Response solveDefaultGrid() throws JsonGenerationException, JsonMappingException, IOException{ 
		Grid grid = service.solveDefaultGrid();
		GridDTO gridDTO = gridDTOConverter.convert(grid);
		String jsonInString = mapper.writeValueAsString(gridDTO);
		
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600").entity(jsonInString).build();
	}
	
	@POST
	@Path("/customized") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON) 
	public Response solveCustomizedGrid(String param) throws JsonGenerationException, JsonMappingException, IOException { 
		GridParameters gridParam = mapper.readValue(param, GridParameters.class);
		Grid grid = service.solveCustomizedGrid(gridParam);
		GridDTO gridDTO = gridDTOConverter.convert(grid);
		String jsonInString = mapper.writeValueAsString(gridDTO);
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600").entity(jsonInString).build();
	}
}
