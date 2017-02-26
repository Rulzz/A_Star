package A_Star_Algo;
import java.io.IOException;
import java.util.ArrayList;

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
	
	@POST 
	@Path("/basic") 
	@Produces(MediaType.APPLICATION_JSON) 
	public Response getBasicGrid(String param) throws JsonGenerationException, JsonMappingException, IOException{ 
		GridParameters gridParam = mapper.readValue(param, GridParameters.class);
		Grid grid = service.getBlankGrid(gridParam);
		GridDTO gridDTO = gridDTOConverter.convert(grid);
		String jsonInString = mapper.writeValueAsString(gridDTO);
		
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600").entity(jsonInString).build();
	}
	
	@POST 
	@Path("/solveCreatedMaze") 
	@Produces(MediaType.APPLICATION_JSON) 
	public Response solveCreatedMaze(String paramAndGrid) throws JsonGenerationException, JsonMappingException, IOException{
		String paramString = paramAndGrid.split("\\|")[0];
		String gridString = paramAndGrid.split("\\|")[1];
		GridParameters gridParam = mapper.readValue(paramString, GridParameters.class);
		GridDTO gridDTOinput = mapper.readValue(gridString, GridDTO.class);
		Grid grid = service.solveCreatedMaze(gridDTOConverter.convert(gridDTOinput), gridParam);
		GridDTO gridDTO = gridDTOConverter.convert(grid);
		String jsonInString = mapper.writeValueAsString(gridDTO);
		
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600").entity(jsonInString).build();
	}
	
	@POST 
	@Path("/repeatedAStar") 
	@Produces(MediaType.APPLICATION_JSON)  
	public Response solveRepeatedAstar(String param) throws JsonGenerationException, JsonMappingException, IOException { 
		GridParameters gridParam = mapper.readValue(param, GridParameters.class);
		ArrayList<Grid> grid = service.solveRepeatedAStar(gridParam);
		ArrayList<GridDTO> gridDTO = gridDTOConverter.convert(grid);
		String jsonInString = mapper.writeValueAsString(gridDTO);
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600").entity(jsonInString).build();
	}
	
	@POST 
	@Path("/adaptiveAStar") 
	@Produces(MediaType.APPLICATION_JSON)  
	public Response solveAdaptiveAStar(String param) throws JsonGenerationException, JsonMappingException, IOException { 
		GridParameters gridParam = mapper.readValue(param, GridParameters.class);
		ArrayList<Grid> grids = service.solveAdaptiveAStar(gridParam);
		ArrayList<GridDTO> gridDTO = gridDTOConverter.convert(grids);
		String jsonInString = mapper.writeValueAsString(gridDTO);
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600").entity(jsonInString).build();
	}
	
}
