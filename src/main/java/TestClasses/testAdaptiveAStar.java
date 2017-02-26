package TestClasses;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import A_Star_Algo.GridResource;
import DTO.GridDTOConverter;

public class testAdaptiveAStar {
	
	ObjectMapper mapper = new ObjectMapper();
	GridDTOConverter gridDTOConverter = new GridDTOConverter();

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		String paramAndGrid = "{\"length\":4,\"breadth\":4,\"xStart\":3,\"yStart\":1,\"xGoal\":3,\"yGoal\":3}|{\"maze\":[[{\"xCoordinate\":0,\"yCoordinate\":0,\"heuristic\":6,\"cellStatus\":\"Blank\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":0,\"yCoordinate\":1,\"heuristic\":5,\"cellStatus\":\"Blank\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":0,\"yCoordinate\":2,\"heuristic\":4,\"cellStatus\":\"Blank\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":0,\"yCoordinate\":3,\"heuristic\":3,\"cellStatus\":\"Blank\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null}],[{\"xCoordinate\":1,\"yCoordinate\":0,\"heuristic\":5,\"cellStatus\":\"Blank\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":1,\"yCoordinate\":1,\"heuristic\":4,\"cellStatus\":\"Block\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":1,\"yCoordinate\":2,\"heuristic\":3,\"cellStatus\":\"Blank\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":1,\"yCoordinate\":3,\"heuristic\":2,\"cellStatus\":\"Blank\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null}],[{\"xCoordinate\":2,\"yCoordinate\":0,\"heuristic\":4,\"cellStatus\":\"Blank\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":2,\"yCoordinate\":1,\"heuristic\":3,\"cellStatus\":\"Block\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":2,\"yCoordinate\":2,\"heuristic\":2,\"cellStatus\":\"Block\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":2,\"yCoordinate\":3,\"heuristic\":1,\"cellStatus\":\"Blank\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null}],[{\"xCoordinate\":3,\"yCoordinate\":0,\"heuristic\":3,\"cellStatus\":\"Blank\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":3,\"yCoordinate\":1,\"heuristic\":2,\"cellStatus\":\"Start\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":3,\"yCoordinate\":2,\"heuristic\":1,\"cellStatus\":\"Block\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null},{\"xCoordinate\":3,\"yCoordinate\":3,\"heuristic\":0,\"cellStatus\":\"Goal\",\"steps\":0,\"stepsTillNow\":0,\"direction\":null}]],\"goalReached\":false}";
		
		GridResource resource = new  GridResource();
		resource.solveCustomizedAdaptiveAStar(paramAndGrid);
		
	}

}
