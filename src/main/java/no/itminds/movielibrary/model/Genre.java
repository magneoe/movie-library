package no.itminds.movielibrary.model;

public class Genre implements Comparable<Genre> {

	private Long id;
	
	private String name;

	public Genre() {}
	public Genre(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + "]";
	}
	@Override
	public int compareTo(Genre o) {
		if(id == o.getId())
			return 0;
		return name.compareTo(o.getName());
	}
}
