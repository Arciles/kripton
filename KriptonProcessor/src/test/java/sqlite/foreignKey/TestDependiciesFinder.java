package sqlite.foreignKey;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.abubusoft.kripton.processor.sqlite.core.EntityUtility;

public class TestDependiciesFinder {

	public static class Dummy {
		public Dummy(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Dummy[name=" + name + "]";
		}

		public final String name;

		public final ArrayList<Dummy> dependencies = new ArrayList<>();
		
		public void dependencies(Dummy ... deps)
		{
			for (Dummy item: deps)
			{
				dependencies.add(item);
			}
			
		}
		
		
	}

	@Test
	public void test01() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);

		check(input);
	}
	
	@Test
	public void test02() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		
		a.dependencies(b);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);

		check(input);
	}
	
	@Test(expected=RuntimeException.class)
	public void test03() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		
		a.dependencies.add(b);
		b.dependencies.add(a);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);

		check(input);
	}
	
	@Test
	public void test04() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		Dummy c = new Dummy("c");
		Dummy d = new Dummy("d");
		
		a.dependencies(b);
		//b.dependencies(a);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);
		input.add(c);
		input.add(d);

		check(input);
	}
	
	@Test
	public void test05() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		Dummy c = new Dummy("c");
		Dummy d = new Dummy("d");
		
		a.dependencies(b, c);
		c.dependencies(d);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);
		input.add(c);
		input.add(d);

		check(input);
	}
	
	@Test(expected=RuntimeException.class)
	public void test06() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		Dummy c = new Dummy("c");
		Dummy d = new Dummy("d");
		
		a.dependencies(c);
		c.dependencies(a);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);
		input.add(c);
		input.add(d);

		check(input);
	}
	
	@Test
	public void test07() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		Dummy c = new Dummy("c");
		Dummy d = new Dummy("d");
		
		a.dependencies(b);
		b.dependencies(c);
		c.dependencies(d);
		//c.dependencies(a);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);
		input.add(c);
		input.add(d);

		check(input);
	}


	public void check(List<Dummy> input) {
		//System.out.println("Input: " + input);
		EntityUtility<Dummy> sorder = new EntityUtility<TestDependiciesFinder.Dummy>(input) {

			@Override
			public List<Dummy> getDependencies(Dummy item) {
				return item.dependencies;
			}

			@Override
			public void generateError(Dummy item) {
				throw new RuntimeException("circular path on " + item.name);
			}
		};

		List<Dummy> output = sorder.order();
		//System.out.println("Output: " + output);
	}
}
