/* Copyright 2016 Google Inc. All Rights Reserved.
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

package com.google.engedu.ghost;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class TrieNode {
    // A map from the next character in the alphabet to the trie node containing those words
    private HashMap<Character, TrieNode> children;
    // If true, this node represents a complete word.
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    /**
     * Add the string as a child of this trie node.
     *
     * @param s String representing partial suffix of a word.
     */
//    public void add(String s) {
//        // TODO(you): add String s to this node.
//        char head = s.charAt(0);
//        if (children.get(head)==null)
//            children.put(head, new TrieNode());
//
//        add(s, children.get(head));
//
//    }

    public void add(String s)
    {
        //base case
        if (s.isEmpty())
        {
            isWord=true;
            //System.out.println("end of word\n");

        }
        //recursive
        else
        {
            char head = s.charAt(0);
            if (!children.containsKey(head))
            {
                children.put(head, new TrieNode());
            }
            //System.out.println("adding "+head);

            //add(s.substring(1),n.children.get(head));
            children.get(head).add(s.substring(1));
        }
    }

    /**
     * Determine whether this node is part of a complete word for the string.
     *
     * @param s String representing partial suffix of a word.
     * @return
     */
//    public boolean isWord(String s) {
//        // TODO(you): determine whether this node is part of a complete word for String s.
//        System.out.println("calling isWord");
//        if (children.get(s.charAt(0))==null)
//            return false;
//        else
//            return isWord(s.substring(1));
//    }

    public boolean isWord (String s)
    {
        //System.out.println("current letter: "+s.charAt(0));
        //base case
        //if it's the end of the word
        if ( s.isEmpty())
        {
        //System.out.println("end of word, "+isWord);
        return isWord;
         }
        //check if null
        else if (!children.containsKey(s.charAt(0)))
        {
            //System.out.println("null node");

            return false;
        }

        //recursive
        else
        {
            String rest = s.substring(1);
            char head = s.charAt(0);
            //System.out.println("recursing");
            //System.out.println("here, head= "+s.charAt(0)+ ", rest= "+rest);

            return children.get(head).isWord(rest);
        }
    }

    /**
     * Find any complete word with this partial segment.
     *
     * @param s String representing partial suffix of a word.
     * @return
     */
    public String getAnyWordStartingWith(String s) {
        // TODO(you):
        Character head;
        String rest;

        if (s.isEmpty()) {
            if (isWord) {
                return "";
            } else {
                Set<Character> keySet = children.keySet();
                if (keySet.isEmpty()) {
                    return null;
                }

                head = keySet.iterator().next();
                rest = "";
            }
        }
        else
        {
            head = s.charAt(0);
            rest = s.substring(0);
            if(!children.containsKey(head))
            {
                return null;
            }

        }

        String word = children.get(head).getAnyWordStartingWith(rest);

        if (word == null)
        {
            return null;
        }
        return head+ word;
    }

    /**
     * Find a good complete word with this partial segment.
     *
     * Definition of "good" left to implementor.
     *
     * @param s String representing partial suffix of a word.
     * @return
     */
    public String getGoodWordStartingWith(String s) {
        return null;
    }
}