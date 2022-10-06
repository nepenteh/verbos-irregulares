package com.jmrh.app.model.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "verbos")
@Entity
public class Verbo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	public String infinitive;
	public String past_simple;
	public String past_participle;
	public String spanish;
	
	public Verbo() {
		
	}

	public Verbo(String infinitive, String past_simple, String past_participle, String spanish) {
		this.infinitive = infinitive;
		this.past_simple = past_simple;
		this.past_participle = past_participle;
		this.spanish = spanish;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInfinitive() {
		return infinitive;
	}

	public void setInfinitive(String infinitive) {
		this.infinitive = infinitive;
	}

	public String getPast_simple() {
		return past_simple;
	}

	public void setPast_simple(String past_simple) {
		this.past_simple = past_simple;
	}

	public String getPast_participle() {
		return past_participle;
	}

	public void setPast_participle(String past_participle) {
		this.past_participle = past_participle;
	}

	public String getSpanish() {
		return spanish;
	}

	public void setSpanish(String spanish) {
		this.spanish = spanish;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, infinitive, past_participle, past_simple, spanish);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Verbo other = (Verbo) obj;
		return Objects.equals(id, other.id) && Objects.equals(infinitive, other.infinitive)
				&& Objects.equals(past_participle, other.past_participle)
				&& Objects.equals(past_simple, other.past_simple) && Objects.equals(spanish, other.spanish);
	}

	@Override
	public String toString() {
		return "Verbo [id=" + id + ", infinitive=" + infinitive + ", past_simple=" + past_simple + ", past_participle="
				+ past_participle + ", spanish=" + spanish + "]";
	}
	
	
	
}
