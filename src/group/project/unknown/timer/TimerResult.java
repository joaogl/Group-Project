/*
 * Copyright 2014 Joao Lourenco and Hampus Backman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package group.project.unknown.timer;

/**
 * Interface for the Timer callbacks.
 * 
 * @author Jo�o Louren�o and Hampus Backman
 */
public abstract class TimerResult {

	/** Instance of any object. */
	protected Object object;

	/**
	 * Constructor for the Timer.
	 * 
	 * @param o , Object to be used.
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public TimerResult(Object o) {
		this.object = o;
	}

	/**
	 * Actual callback function.
	 * 
	 * @param caller , What timer is calling it.
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public abstract void timerCall(String caller);

}