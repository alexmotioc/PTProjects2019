package polynomial;

import java.util.ArrayList;
import java.util.Collections;

public class Polynomial {

	private ArrayList<Monomial> MList = new ArrayList<Monomial>();

	public ArrayList<Monomial> getMList() {
		return MList;
	}

	public void setMList(ArrayList<Monomial> mList) {
		MList = mList;
	}
	
	public void addMonomial(Monomial m)
	{
		MList.add(m);
	}
	
	public void removeMonomial(Monomial m)
	{
		MList.remove(m);
	}
	
	public int getMaxDegree()
	{
		Collections.sort(MList);
		
		return MList.get(0).getDegree();
	}
	
	public void simplify()
	{
		Collections.sort(MList);
		for(int i=0;i<MList.size()-1;i++)
		{
			if(MList.get(i).getDegree() == MList.get(i+1).getDegree())
			{
				MList.get(i).setCoeff(MList.get(i).getCoeff() + MList.get(i+1).getCoeff());
				MList.remove(i+1);
				i--;
			}
		}
		
		for(int i=0;i<MList.size();i++)
		{
			if(MList.get(i).getCoeff() == 0)
			{
				MList.remove(i);
				i--;
			}
		}
	}
	
	public boolean equals(Polynomial p) {
		Collections.sort(MList);
		Collections.sort(p.getMList());
		if(MList.size()!=p.getMList().size()) {
			return false;
		}
		for(int i=0;i<MList.size();i++)
			if(!MList.get(i).equals(p.getMList().get(i))) {
				return false;
			}
		return true;
	}
	
	public String toString() {
		String s="";
		for(Monomial i:MList) {
			s+=i + "+";
		}
		if(s.length()>0)
		s=s.substring(0, s.length()-1);
		return s;
	}
	
	public Polynomial add(Polynomial p1,Polynomial p2)
	{
		Polynomial p3 = new Polynomial();
		for(Monomial i : p1.getMList())
		{
			p3.addMonomial(i);
		}
		
		for(Monomial i : p2.getMList())
		{
			p3.addMonomial(i);
		}
		
		p3.simplify();
		
		return p3;
	}
	
	public Polynomial sub(Polynomial p1,Polynomial p2)
	{
		Polynomial p3 = new Polynomial();
		for(Monomial i : p1.getMList())
		{
			p3.addMonomial(i);
		}
		
		for(Monomial i : p2.getMList())
		{
			p3.addMonomial(new Monomial(i.getCoeff()*(-1),i.getDegree()));
		}
		
		p3.simplify();
		
		return p3;
	}
	
	public Polynomial mul(Polynomial p1,Polynomial p2)
	{
		Polynomial p3 = new Polynomial();
		
		for(Monomial i: p1.getMList())
		{
			for(Monomial j: p2.getMList())
			{
				p3.addMonomial(new Monomial(i.getCoeff() * j.getCoeff(),i.getDegree() + j.getDegree()));
			}
		}
		p3.simplify();
		
		return p3;
		
	}
	
	public Polynomial derive(Polynomial p)
	{
		Polynomial p3 = new Polynomial();
		
		for(Monomial i : p.getMList())
		{
			if(i.getDegree()!= 0)
				p3.addMonomial(new Monomial(i.getCoeff() * i.getDegree(),i.getDegree() - 1));
		}
		
		p3.simplify();
		
		return p3;
	}
	
	public Polynomial integrate(Polynomial p)
	{
		Polynomial p3 = new Polynomial();
		
		for(Monomial i : p.getMList())
		{
			//if(i.getDegree()!= 0)
				p3.addMonomial(new Monomial(i.getCoeff() / (i.getDegree() + 1),i.getDegree() + 1));
		}
		
		p3.simplify();
		
		return p3;
	}
	
	public ArrayList<Polynomial> divide(Polynomial p1,Polynomial p2)
	{
		ArrayList<Polynomial> p3=new ArrayList<Polynomial>();
		Polynomial p3q = new Polynomial();
		Polynomial p3r = new Polynomial();
		
		Collections.sort(p1.getMList());
		Collections.sort(p2.getMList());
		
		for(Monomial i : p1.getMList())
		{
			p3r.addMonomial(new Monomial(i.getCoeff(),i.getDegree())); // r = n
		}
		
		
		while(!p3r.getMList().isEmpty() && p3r.getMaxDegree() >= p2.getMaxDegree()) //r != 0 && degree(r) >= degree(d)
		{
			Polynomial t = new Polynomial();
			t.addMonomial(p3r.getMList().get(0).divide(p2.getMList().get(0))); //lead(r) / lead(d)
			p3q = p3q.add(p3q, t); //q = q + t
			p3r = p3r.sub(p3r, t.mul(t,p2)); // r = r - t * d
			t.getMList().remove(0);
			p3r.simplify();
		}
		//System.out.println(p3r);
		p3.add(p3q);
		p3.add(p3r);
		//System.out.println(p3.get(1));
		return p3;
		
		
	}
	
	
	
}
