package com.porus.CacheDemo.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Abstract Data Type (ADT) and persistent entity modeling the results of a mathematical calculation.
 *
 * @author John Blum
 * @see java.io.Serializable
 * @see jakarta.persistence.Entity
 * @see jakarta.persistence.IdClass
 * @see jakarta.persistence.Table
 * @since 1.1.0
 */
// tag::class[]
@Entity
@Getter
@IdClass(ResultHolder.ResultKey.class)
@EqualsAndHashCode(of = { "operand", "operator" })
@RequiredArgsConstructor(staticName = "of")
@Table(name = "Calculations")
public class ResultHolder implements Serializable {

	@Id @NonNull
	private Integer operand;

	@Id
	@NonNull
	@Enumerated(EnumType.STRING)
	private Operator operator;

	@NonNull
	private Integer result;

	protected ResultHolder() { }

	@Override
	public String toString() {
		return getOperator().toString(getOperand(), getResult());
	}

	@Getter
	@EqualsAndHashCode
	@RequiredArgsConstructor(staticName = "of")
	public static class ResultKey implements Serializable {

		@NonNull
		private Integer operand;

		@NonNull
		private Operator operator;

		protected ResultKey() { }

	}
}