package com.mikeyd.ipldashboard.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

    public class MatchDataProcessor implements ItemProcessor<MatchInput, Person> {

        private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

        @Override
        public Person process(final Person person) {
            final String firstName = person.firstName().toUpperCase();
            final String lastName = person.lastName().toUpperCase();

            final Person transformedPerson = new Person(firstName, lastName);

            log.info("Converting (" + person + ") into (" + transformedPerson + ")");

            return transformedPerson;
        }

    }
}
