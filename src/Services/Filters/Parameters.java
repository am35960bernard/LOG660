package Services.Filters;

import java.util.List;

public class Parameters {
	
	private String name;
	private List<String> values;
	
	public Parameters(String name, List<String> values)
	{
		this.name = name;
		this.values = values;
	}
	
	public String getName()
	{
		return name;
	}
	
	public List<String> getValues()
	{
		return values;
	}

}
